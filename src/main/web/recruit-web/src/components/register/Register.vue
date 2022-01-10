<template>
  <div class="register-block">
    <div class="register-subject">
      <el-form label-position="left" label-width="75px" style="margin: 50px;">

        <!-- multiple -->
        <transition name="el-fade-in-linear">
          <!-- 账号注册页-->
          <div v-show="page == 0">
            <el-upload class="avatar-uploader" v-loading="avatarLoading" align="center" :action="uploadUrl"
              :show-file-list="false" :on-success="handleAvatarSuccess" style="margin-bottom: 50px;" :before-upload="beforeAvatarUpload">
              <img v-if="registerData.avatar" :src="registerData.avatar" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon">上传头像</i>
            </el-upload>

            <el-form-item label="手机号:">
              <el-input v-model="registerData.username" prefix-icon="el-icon-user" class="login-subject-input"
                placeholder="请输入手机号"></el-input>
            </el-form-item>

            <el-form-item label="密码:">
              <el-input v-model="registerData.password" show-password prefix-icon="el-icon-lock" class="login-subject-input"
                placeholder="请输入密码"></el-input>
            </el-form-item>

            <el-form-item label="确认密码:">
              <el-input v-model="registerData.passwordAgain" show-password prefix-icon="el-icon-lock" class="login-subject-input"
                placeholder="请再次确认密码"></el-input>
            </el-form-item>

            <el-form-item class="register-buttom">
              <el-button type="info" style="background: #5dd5c8;" @click="nextPage(-1)">企业注册</el-button>
              <el-button type="info" style="background: #5dd5c8;" @click="nextPage(1)">学生注册</el-button>
            </el-form-item>
          </div>
        </transition>

        <transition name="el-fade-in-linear">
          <!-- 学习信息第一页 -->
          <div v-show="page == 1">
            <el-form-item label="姓名:">
              <el-input v-model="registerData.name" prefix-icon="el-icon-s-custom" class="login-subject-input"
                placeholder="请输入你的姓名"></el-input>
            </el-form-item>

            <el-form-item label="性别:">
              <el-radio-group v-model="registerData.sex">
                <el-radio label="1">男</el-radio>
                <el-radio label="0">女</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="出生年月:">
              <el-date-picker v-model="registerData.birthday" type="month" value-format="yyyy-MM" placeholder="选择月">
              </el-date-picker>
            </el-form-item>

            <el-form-item label="学校:">
              <el-input v-model="registerData.school" prefix-icon="el-icon-school" class="login-subject-input"
                placeholder="请输入你的所在学校"></el-input>
            </el-form-item>

            <el-form-item label="专业:">
              <el-input v-model="registerData.major" prefix-icon="el-icon-copy-document" class="login-subject-input"
                placeholder="请输入你的所学专业"></el-input>
            </el-form-item>

            <el-form-item label="学历:">

              <el-select v-model="registerData.education" placeholder="请选择学历信息">
                <el-option value="高中及以下"></el-option>
                <el-option value="大专"></el-option>
                <el-option value="本科"></el-option>
                <el-option value="硕士"></el-option>
                <el-option value="博士及以上"></el-option>
              </el-select>

            </el-form-item>

            <el-form-item label="就读时间:">
              <el-date-picker v-model="registerData.studyDate" type="monthrange" value-format="yyyy" range-separator="至"
                start-placeholder="开始时间" end-placeholder="结束时间">
              </el-date-picker>
            </el-form-item>

            <el-form-item class="register-buttom">
              <el-button type="info" style="background: #5dd5c8;" @click="breakPage(-1)">上一步</el-button>
              <el-button type="info" style="background: #5dd5c8;" @click="nextPage(1)">下一步</el-button>
            </el-form-item>
          </div>
        </transition>

        <transition name="el-fade-in-linear">
          <!-- 学习信息第二页 -->
          <div v-show="page == 2">

            <el-form-item label="预期岗位:">
              <el-cascader v-model="registerData.position" :options="position" :show-all-levels="false" placeholder="搜索"
                filterable :props="{ expandTrigger: 'hover',value: 'name',label: 'name' }"></el-cascader>
            </el-form-item>

            <el-form-item label="预期薪资:">
              <el-input-number v-model="registerData.waves" :step="1" :precision="0" :max="100" :min="0" controls-position="right"></el-input-number>
              <span style="margin-left: 15px;">K</span>
            </el-form-item>

            <span style="font-size: 14px;margin: 15px 0 ;display: block;">自我简介:</span>
            <rich-text :height="250" :size="'mini'" ref="richText" v-if="page > 0" ></rich-text>

            <el-form-item class="register-buttom">
              <el-button type="info" style="background: #5dd5c8;" @click="breakPage(-1)">上一步</el-button>
              <el-button type="info" style="background: #5dd5c8;" @click="nextPage()">完成</el-button>
            </el-form-item>
          </div>
        </transition>

        <transition name="el-fade-in-linear">
          <!-- 企业注册信息第一页 -->
          <div v-show="page == -1">
            <el-form-item label="公司全称:">
              <el-input v-model="registerData.fullName" prefix-icon="el-icon-info" class="login-subject-input"
                placeholder="请输入公司全称:"></el-input>
            </el-form-item>

            <el-form-item label="公司简称:">
              <el-input v-model="registerData.name" prefix-icon="el-icon-s-custom" class="login-subject-input"
                placeholder="简称"></el-input>
            </el-form-item>

            <el-form-item label="所属行业:">
              <el-cascader v-model="registerData.industry" :options="industry" placeholder="搜索" filterable :props="{ expandTrigger: 'hover',value: 'name',label: 'name' }"></el-cascader>
            </el-form-item>

            <el-form-item label="人员规模:">
              <el-select v-model="registerData.scale" placeholder="请选择人员规模">
                <el-option value="0-20人"></el-option>
                <el-option value="20-99人"></el-option>
                <el-option value="100-499人"></el-option>
                <el-option value="500-999人"></el-option>
                <el-option value="1000-9999人"></el-option>
                <el-option value="10000人以上"></el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="所在市区:">
              <el-cascader v-model="registerData.address" :options="area" placeholder="搜索" filterable :props="{ expandTrigger: 'hover',value: 'name',label: 'name' }"></el-cascader>
            </el-form-item>

            <el-form-item label="详细地址:">
              <el-input v-model="registerData.detailAddress" type="textarea" :rows="4" prefix-icon="el-icon-office-building"
                class="login-subject-input" placeholder="请输入公司详细地址"></el-input>
            </el-form-item>



            <el-form-item class="register-buttom">
              <el-button type="info" style="background: #5dd5c8;" @click="breakPage(1)">上一步</el-button>
              <el-button type="info" style="background: #5dd5c8;" @click="nextPage(-1)">下一步</el-button>
            </el-form-item>
          </div>
        </transition>

        <transition name="el-fade-in-linear">
          <!-- 企业注册信息第一页 -->
          <div v-show="page == -2">
            <span style="font-size: 14px;margin: 15px 0 ;display: block;">公司介绍:</span>
            <rich-text :height="400" :size="'mini'" ref="richText" v-if="page < 0"></rich-text>
            <el-form-item class="register-buttom">
              <el-button type="info" style="background: #5dd5c8;" @click="breakPage(1)">上一步</el-button>
              <el-button type="info" style="background: #5dd5c8;" @click="nextPage()">完成</el-button>
            </el-form-item>
          </div>
        </transition>
      </el-form>
    </div>
  </div>
</template>

<script>
  import RichText from '../../common/richText/RichText.vue'
  import {
    mapState,
  } from 'vuex'
  /**
   * description 注册界面
   */
  export default {
    name: 'Register',
    components: {
      RichText
    },
    computed: {
      ...mapState(['position','area','industry'])
    },
    data() {
      return {
        avatarLoading: false,
        imageArr: ["image/png", "image/jpg", "image/gif", "image/jpeg", "image/bmp"],
        uploadUrl: this.$common.baseUrl + "upload/img",
        registerData: {
          username: '',
          password: '',
          passwordAgain: '',
          avatar: '',
          name: '',
          sex: '',
          birthday: '',
          school: '',
          major: '',
          education: '',
          studyDate: null,
          position: '',
          waves: '',
          describes: '',
          fullName: '',
          scale: '',
          address: '',
          detailAddress: '',
          industry: ''
        },

        page: 0, //
      }
    },
    methods: {
      // 上传头像后显示头像图片
      handleAvatarSuccess(res, file) {
        //this.registerData.avatar = URL.createObjectURL(file.raw);
        this.registerData.avatar = res.link
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
      // 注册页的前进后台
      nextPage(n) {
        // 翻页前先校验当前页
        switch (this.page) {
          // 用户名密码页
          case 0:
            if (!this.checkBlank([this.registerData.avatar, '头像'], [this.registerData.username, '手机号'],
                [this.registerData.password, '密码'], [this.registerData.passwordAgain, '确认密码'])) {
              return false
            }

            if (!/^[1][3,4,5,7,8,9][0-9]{9}$/.test(this.registerData.username)) {
              this.$message("手机号格式不正确")
              return false
            }

            if (this.registerData.password != this.registerData.passwordAgain) {
              this.$message("两次密码输入不一致")
              return false
            }

            this.$axios.checkUsername({
              username: this.registerData.username
            }).then((res) => {
              if (!res.data) {
                this.$message('手机号已被注册');
              } else {
                this.page += n
              }
            })
            break;
            // 学生注册第一页
          case 1:
            if (!this.checkBlank([this.registerData.name, '姓名'], [this.registerData.sex, '性别'],
                [this.registerData.birthday, '出生年月'], [this.registerData.school, '学校'],
                [this.registerData.major, '专业'], [this.registerData.education, '学历'],
                [this.registerData.studyDate, '就读时间'])) {
              return false
            }
            this.page += n
            break;
            // 学生注册第二页
          case 2:
            if (!this.checkBlank([this.registerData.position, '岗位'], [this.registerData.waves, '薪水'])) {
              return false
            }
            this.registerPersonal()
            break;
            // 公司注册第一页
          case -1:
            if (!this.checkBlank([this.registerData.fullName, '公司全称'], [this.registerData.name, '公司简称'],
            [this.registerData.industry, '行业'], [this.registerData.scale, '规模'],
            [this.registerData.address, '所在市区'], [this.registerData.detailAddress, '公司详细地址'])) {
              return false
            }
            this.page += n
            break;
            // 公司注册第二页
          case -2:
            this.registerEnterprise()
            break;
        }
      },
      // 返回上一页不需要校验当前页
      breakPage(n) {
        this.page += n
      },
      // 校验是否为空，为空则提示
      checkBlank() {
        for (let i of arguments) {
          if (i[0] == null || i[0] == '') {
            this.$message("请输入" + i[1])
            return false
          }
        }
        return true
      },
      // 学生注册提交
      registerPersonal() {
        let data = { ...this.registerData
        }
        data.describes = encodeURIComponent(this.$refs.richText.froalaContent)
        data.position = data.position.join("-")
        data.studyDate = data.studyDate.join("-")
        this.$axios.registerPersonal(data).then(res => {
          this.$message({
            message: '注册成功',
            type: 'success'
          });
          this.$router.push({
            path: '/login'
          });
        })
      },
      // 公司注册提交
      registerEnterprise(){
        let data = { ...this.registerData
        }
        data.describes = encodeURIComponent(this.$refs.richText.froalaContent)
        data.address = data.address.join("-")
        data.industry = data.industry.join("-")
        console.log(data);

        this.$axios.registerCompany(data).then(res => {
          this.$message({
            message: '注册成功',
            type: 'success'
          });
          this.$router.push({
            path: '/login'
          });
        })
      }
    },
    created() {

    }
  }
</script>

<style scoped>
  .register-block {
    height: 100%;
    background: #5dd5c8 url(../../assets/newbg.png) center bottom no-repeat;
  }

  .register-subject {
    position: absolute;
    top: 50%;
    left: 50%;
    width: 460px;
    height: 650px;
    border-radius: 2%;
    background: #FFFFFF;
    margin: -325px 0 0 -230px;
  }

  .avatar-uploader-icon {
    font-size: 12px;
    color: #8c939d;
    width: 90px;
    height: 90px;
    line-height: 90px;
    text-align: center;
  }

  .avatar {
    width: 90px;
    height: 90px;
    display: block;
  }

  .register-buttom {
    position: absolute;
    bottom: 25px;
  }

  .register-buttom button {
    margin-right: 45px;
  }
</style>
