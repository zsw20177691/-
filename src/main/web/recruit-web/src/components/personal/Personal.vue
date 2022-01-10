<template>
  <div class="company-box">
    <el-card class="box-card">
      <!-- 导航栏 -->
      <el-menu active-text-color="#5dd5c8" default-active="2" @select="handleSelect">



<!--        <el-menu-item index="1">
          <i class="el-icon-s-order"></i>
          <span slot="title">求职信息</span>
        </el-menu-item> -->

        <el-menu-item index="2">
          <i class="el-icon-info"></i>
          <span slot="title">个人信息</span>
        </el-menu-item>
        <el-menu-item index="3">
          <i class="el-icon-info"></i>
          <span slot="title">工作状况</span>
        </el-menu-item>
      </el-menu>
    </el-card>

    <el-card class="company-body">
      <!-- 求职信息 -->
<!--      <div v-show="box == '1'">
      </div> -->
      <!-- 个人信息 -->
      <div v-show="box == '2'">
        <personal-info></personal-info>
      </div>
      <div v-show="box == '3'">
        <work_Info></work_Info>
      </div>

    </el-card>

  </div>
</template>

<script>
  import PersonalInfo from './PersonalInfo'
  import work_Info from './work_Info'
  /**
   * description
   */
  export default {
    name: 'Personal',
    components:{
      PersonalInfo,
      work_Info
    },
    data() {
      return {
        box: '2',
      }
    },
    methods: {
      handleSelect(key, keyPath) {
        this.box = key
      },
    },
  }
</script>

<style scoped>
  .company-box {
    width: 1200px;
    margin: 0 auto;
    margin-top: 20px;
    height: 600px;
    /* position: absolute; */
  }

  .box-card {
    width: 160px;
    float: left;
    /* margin-top: 20px; */
  }

  .el-menu {
    border-right: solid 0px #e6e6e6;
  }

  .company-body {
    margin-left: 180px;
    height: 100%;
  }
</style>
