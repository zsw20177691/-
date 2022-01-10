<template>
  <div class="user-info">

    <el-row :gutter="20">
      <el-col :span="4">
        <span class="info-title">简称：</span>
      </el-col>
      <el-col :span="8">
        <span v-text="data.name"></span>
      </el-col>
      <el-col :span="4">
        <span class="info-title">全称：</span>
      </el-col>
      <el-col :span="8">
        <span v-text="data.fullName"></span>
      </el-col>
    </el-row>

    <el-row :gutter="20">
      <el-col :span="4">
        <span class="info-title">行业：</span>
      </el-col>
      <el-col :span="8">
        <span v-text="data.industry"></span>
      </el-col>
      <el-col :span="4">
        <span class="info-title">人员规模：</span>
      </el-col>
      <el-col :span="8">
        <span v-text="data.scale"></span>
      </el-col>
    </el-row>

    <el-row :gutter="20">
      <el-col :span="4">
        <span class="info-title">市区地址：</span>
      </el-col>
      <el-col :span="8">
        <span v-text="data.address"></span>
      </el-col>
      <el-col :span="4">
        <span class="info-title">详细地址：</span>
      </el-col>
      <el-col :span="8">
        <span v-text="data.detailAddress"></span>
      </el-col>
    </el-row>


    <span class="info-title">公司介绍：</span>
    <div class="fr-view" v-html="data.describes">

    </div>
  </div>
</template>

<script>
  /**
   * description
   */
  export default {
    name: 'CompanyInfo',
    data() {
      return {
        data: {

        }
      }
    },
    methods: {},
  }
</script>

<style scoped>
  .user-info .fr-view {
    padding: 20px;
    border: solid 2px gray;
    border-radius: 5px;
    margin: 10px 0;
  }

  .user-info .info-title {
    font-weight: 800;
  }

  .user-info span {
    margin: 7px 0;
    display: inline-block;
  }

  .user-info a {
    width: 120px;
    display: block;
    float: right;
    height: 45px;
    line-height: 45px;
    background: #5dd5c8;
    color: white;
    text-align: center;
    cursor: pointer;
    text-decoration: none;
  }


  .user-info a:hover {
    background: #67f7e7;
  }
</style>
