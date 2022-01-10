<template>
  <div class="top-nav">
    <div style="width: 1200px;margin: 0 auto;">

      <el-menu class="subject-nav" default-active="1" mode="horizontal" background-color="#545c64" text-color="#ffffff"
        active-text-color="#5dd5c8" @select="handleSelect">

        <el-menu-item index="9" disabled>
          <span slot="title" class="title-nav">毕业生招聘</span>
        </el-menu-item>

        <el-menu-item index="1">主页</el-menu-item>

        <el-menu-item index="2" v-show="user.type == 2">招聘管理</el-menu-item>

        <el-menu-item index="3" v-show="user.type == 0">后台</el-menu-item>

        <el-menu-item index="4" v-show="user.type == 1">个人中心</el-menu-item>

        <el-submenu index="0" class="my-blck" v-show="user.id != null">
          <template slot="title">
            <div style="display: inline-block;">
              {{user.name}}
              <el-avatar size="small" :src="user.avatar"></el-avatar>
            </div>
          </template>
          <el-menu-item index="0-1">个人设置</el-menu-item>
          <el-menu-item index="0-2">修改密码</el-menu-item>
          <el-menu-item index="0-3">退出登录</el-menu-item>
        </el-submenu>

        <el-menu-item index="9" class="my-blck" v-show="user.id == null">
          <router-link :to="{name:'Login'}">
            <el-button type="info" round size="mini" style="background: #5dd5c8;">登录</el-button>
          </router-link>
        </el-menu-item>

      </el-menu>
    </div>

    <el-dialog title="个人信息" :visible.sync="dialogInfo" width="30%">
      <el-form label-position="left" label-width="75px">
        <el-upload class="avatar-uploader" v-loading="avatarLoading" align="center" :action="uploadUrl" :show-file-list="false"
          :on-success="handleAvatarSuccess" style="margin-bottom: 50px;" :before-upload="beforeAvatarUpload">
          <img v-if="info.avatar" :src="info.avatar" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon">上传头像</i>
        </el-upload>

        <el-form-item label="姓名:">
          <el-input v-model="info.name" prefix-icon="el-icon-s-custom" class="login-subject-input" placeholder="请输入你的姓名"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editUserInfo()">修改</el-button>
      </span>
    </el-dialog>

    <el-dialog title="修改密码" :visible.sync="dialogPassword" width="30%">
      <el-form label-position="left" label-width="75px">
        <el-form-item label="原密码:">
          <el-input v-model="pw.oldPw" show-password prefix-icon="el-icon-lock" class="login-subject-input" placeholder="请输入原密码"></el-input>
        </el-form-item>

        <el-form-item label="新密码:">
          <el-input v-model="pw.newPw" show-password prefix-icon="el-icon-lock" class="login-subject-input" placeholder="请输入新密码"></el-input>
        </el-form-item>

        <el-form-item label="确认密码:">
          <el-input v-model="pw.newPwAgain" show-password prefix-icon="el-icon-lock" class="login-subject-input"
            placeholder="请确认新密码"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editUserPassword()">修改</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
  import {
    mapState,
    mapMutations
  } from 'vuex'

  /**
   * description  顶部导航栏
   */
  export default {
    name: 'Nav',
    computed: {
      ...mapState(['user'])
    },
    data() {
      return {
        dialogInfo: false,
        dialogPassword: false,
        avatarLoading: false,
        uploadUrl: this.$common.baseUrl + "upload/img",
        imageArr: ["image/png", "image/jpg", "image/gif", "image/jpeg", "image/bmp"],
        info: {
          avatar: '',
          name: ''
        },
        pw: {
          oldPw: '',
          newPw: '',
          newPwAgain: ''
        }
      }
    },
    methods: {
      ...mapMutations({
        changeUser: 'changeUser'
      }),
      // 上传头像后显示头像图片
      handleAvatarSuccess(res, file) {
        this.info.avatar = res.link
        this.avatarLoading = false
      },
      // 上传图片校验
      beforeAvatarUpload(file) {
        let isJPG = false
        for (let type of this.imageArr) {
          if (file.type === type) {
            isJPG = true
            break
          }
        }
        if (!isJPG) {
          this.$message.error('图片格式错误');
          return false
        }
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
          return false
        }

        this.avatarLoading = true
        return true;
      },
      // nav点击触发
      handleSelect(key, keyPath) {
        switch (key) {
          case '1':
            if (this.$route.path != '/') {
              this.$router.push({
                path: '/'
              });
            }
            break;
          case '2':
            if (this.$route.path != '/company') {
              this.$router.push({
                path: '/company'
              });
            }
            break;
          case '3':
            if (this.$route.path != '/admin') {
              this.$router.push({
                path: '/admin'
              });
            }
            break;
          case '4':
            if (this.$route.path != '/personal') {
              this.$router.push({
                path: '/personal'
              });
            }
            break;
          case '0-1':
            this.dialogInfo = true
            this.info.avatar = this.user.avatar
            this.info.name = this.user.name
            break;
          case '0-2':
            this.dialogPassword = true
            break;
          case '0-3':
            this.logOut()
            break;
        }
      },
      // 登出
      logOut() {
        this.changeUser({
          id: null,
          name: "请登录",
          avatar: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
          type: null
        })
        this.$router.push({
          path: '/'
        });
      },
      // 修改头像用户名
      editUserInfo() {
        if (this.info.name == '') {
          this.$message("姓名不能为空")
          return
        }
        this.$confirm('此操作将修改个人信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let param = { ...this.info
          }
          this.$axios.editUser(param).then(res => {
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
            this.user.avatar = this.info.avatar
            this.user.name = this.info.name
            this.changeUser(this.user)
            this.dialogInfo = false
          })
        })
      },
      // 修改用户密码
      editUserPassword() {
        if (this.pw.oldPw == '') {
          this.$message("请输入原密码")
          return
        }
        if (this.pw.newPw == '') {
          this.$message("请输入新密码")
          return
        }
        if (this.pw.newPwAgain == '') {
          this.$message("请输入确认密码")
          return
        }

        this.$confirm('此操作将修改个人密码, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let param = { ...this.pw
          }
          this.$axios.editUserPassword(param).then(res => {
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
            this.dialogPassword = false
          })
        })
      }
    },
  }
</script>

<style scoped>
  .el-menu.el-menu--horizontal {
    border-bottom: solid 0px #e6e6e6;
  }

  .el-menu-item.is-disabled {
    opacity: 1;
  }

  .title-nav {
    color: #72fbec;
    font-weight: 900;
    font-size: 20px;
    letter-spacing: 4px;
  }

  .top-nav {
    height: 50px;
    width: 100%;
    background: #545c64;
  }

  .el-menu--horizontal>.el-menu-item {
    height: 50px;
    line-height: 50px;
  }

  .subject-nav {
    margin: 0 auto;
  }

  .el-menu-item.is-disabled {
    cursor: pointer;
  }

  .avatar {
    width: 90px;
    height: 90px;
    display: block;
  }
</style>
