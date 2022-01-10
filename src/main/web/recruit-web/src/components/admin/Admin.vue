<template>
  <div class="company-box">
    <el-card class="box-card">
      <!-- 导航栏 -->
      <el-menu active-text-color="#5dd5c8" default-active="2" @select="handleSelect">

        <el-menu-item index="2">
          <i class="el-icon-s-order"></i>
          <span slot="title">用户管理</span>
        </el-menu-item>

      </el-menu>
    </el-card>

    <el-card class="company-body">
      <div v-show="box == '2'">
        <user-table></user-table>
      </div>

    </el-card>
  </div>
</template>

<script>
  import UserTable from './UserTable'
  /**
   * description
   */
  export default {
    name: 'Admin',
    components:{
      UserTable
    },
    data() {
      return {
        box: '1',
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
