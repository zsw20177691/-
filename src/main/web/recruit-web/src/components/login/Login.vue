<template>
  <div class="login-block">
    <div class="login-subject">

      <el-form label-width="80px" style="margin: 50px;">

        <el-input v-model="username" prefix-icon="el-icon-user" class="login-subject-input" placeholder="手机号"></el-input>

        <el-input v-model="password" show-password prefix-icon="el-icon-lock" class="login-subject-input" placeholder="密码"></el-input>

        <el-button type="primary" style="width: 100%;background: #5dd5c8;" @click="onLogin">登录</el-button>

        <div>
          <router-link :to="{name:'Register'}">
            <a style="float: right;margin-top: 15px;color: #5dd5c8;">免费注册</a>
          </router-link>
        </div>

      </el-form>
    </div>
  </div>
</template>

<script>
  import {
    mapState,
    mapMutations
  } from 'vuex'

  /**
   * description  登录界面
   */
  export default {
    name: 'Login',
    data() {
      return {
        username: '',
        password: '',
      }
    },
    methods: {
      ...mapMutations({
        changeUser: 'changeUser',
      }),
      onLogin() {
        if (this.username == '' || this.password == '') {
          this.$message('请填写好手机号、密码');
          return
        }
        this.$axios.login({
          username: this.username,
          password: this.password
        }).then((res) => {
          this.$message({
            message: '登录成功',
            type: 'success'
          });

          this.changeUser(res.data)

          if(res.data.type == 0){
            this.$router.push({
              path: '/admin'
            });
          }else if(res.data.type == 1){
            this.$router.push({
              path: '/'
            });
          }else if(res.data.type == 2){
            this.$router.push({
              path: '/company'
            });
          }
        })
      }
    },
  }
</script>

<style scoped>
  .login-block {
    height: 100%;
    background: #5dd5c8 url(../../assets/newbg.png) center bottom no-repeat;
  }

  .login-subject {
    position: absolute;
    top: 50%;
    left: 50%;
    width: 500px;
    height: 400px;
    border-radius: 2%;
    background: #FFFFFF;
    margin: -220px 0 0 -250px;
  }

  .login-subject-input {
    margin-bottom: 35px;
  }
</style>
