<template>
  <div class="company-box">
    <el-card class="box-card">
      <!-- 导航栏 -->
      <el-menu active-text-color="#5dd5c8" default-active="1" @select="handleSelect">
        <el-menu-item index="1">
          <i class="el-icon-folder-opened"></i>
          <span slot="title">招聘管理</span>
        </el-menu-item>
        <el-menu-item index="2">
          <i class="el-icon-folder-add"></i>
          <span slot="title">新增招聘</span>
        </el-menu-item>

        <el-menu-item index="4">
          <i class="el-icon-s-order"></i>
          <span slot="title">求职信息</span>
        </el-menu-item>

        <el-menu-item index="3">
          <i class="el-icon-info"></i>
          <span slot="title">公司信息</span>
        </el-menu-item>

      </el-menu>
    </el-card>

    <el-card class="company-body">
      <!-- 招聘管理 -->
      <div v-show="box == '1'">
        <recruit-table></recruit-table>
      </div>
      <!-- 新增招聘 -->
      <div v-show="box == '2'">
        <recruit-add></recruit-add>
      </div>
      <!-- 公司信息 -->
      <div v-show="box == '3'">
        <company-info></company-info>
      </div>
      <!-- 求职信息 -->
      <div v-show="box == '4'">
        <delivery-table></delivery-table>
      </div>
    </el-card>

  </div>
</template>

<script>
  import CompanyInfo from './CompanyInfo.vue'
  import RecruitTable from './RecruitTable.vue'
  import RecruitAdd from './RecruitAdd.vue'
  import DeliveryTable from './DeliveryTable.vue'
  /**
   * description  公司页面
   */
  export default {
    name: 'Company',
    components: {
      RecruitAdd,
      RecruitTable,
      CompanyInfo,
      DeliveryTable
    },
    computed: {

    },
    data() {
      return {
        box: '1',
      }
    },
    methods: {
      // 导航栏按钮点击
      handleSelect(key, keyPath) {
        this.box = key
      },
    },
    created() {
      // const wsuri = "ws://127.0.0.1:8080/WebSocket";
      //  this.websock = new WebSocket(wsuri);
    }
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
