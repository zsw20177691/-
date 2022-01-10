<template>
  <div class="user-info">
    <el-row :gutter="20" v-show="showStatus == true">
      <el-col :span="4">
        <span class="info-title">状态：</span>
      </el-col>
      <el-col :span="8">
        <span v-text="data.status == 1 ? '已就业' : '待业'" style="color: red;"></span>
      </el-col>
      <el-col :span="4">
        <span class="info-title" v-show="data.status == 1">企业：</span>
      </el-col>
      <el-col :span="8">
        <span v-show="data.status == 1">{{data.company}}</span>
      </el-col>
    </el-row>

    <el-row :gutter="20">
      <el-col :span="4">
        <span class="info-title">姓名：</span>
      </el-col>
      <el-col :span="8">
        <span v-text="data.name"></span>
      </el-col>
      <el-col :span="4">
        <span class="info-title">性别：</span>
      </el-col>
      <el-col :span="8">
        <span v-text="data.sex == 1 ? '男' : '女'"></span>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="4">
        <span class="info-title">生日：</span>
      </el-col>
      <el-col :span="8">
        <span v-text="data.birthday"></span>
      </el-col>
      <el-col :span="4">
        <span class="info-title">学历：</span>
      </el-col>
      <el-col :span="8">
        <span v-text="data.education"></span>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="4">
        <span class="info-title">学校：</span>
      </el-col>
      <el-col :span="8">
        <span v-text="data.school"></span>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="4">
        <span class="info-title">专业：</span>
      </el-col>
      <el-col :span="8">
        <span v-text="data.major"></span>
      </el-col>
      <el-col :span="4">
        <span class="info-title">在校时间：</span>
      </el-col>
      <el-col :span="8">
        <span v-text="data.studyDate"></span>
      </el-col>
    </el-row>

    <el-row :gutter="20">
      <el-col :span="4">
        <span class="info-title">预期岗位：</span>
      </el-col>
      <el-col :span="8">
        <span v-text="data.position"></span>
      </el-col>
      <el-col :span="4">
        <span class="info-title">预期薪资：</span>
      </el-col>
      <el-col :span="8">
        <span v-text="data.waves"></span>
      </el-col>
    </el-row>

    <span class="info-title">自我简介：</span>
    <div class="fr-view" v-html="data.describes">

    </div>

    <div style="height: 45px;">
      <a :href="data.resumeUrl" target="_blank">下载简历</a>
    </div>

  </div>
</template>

<script>
  /**
   * description
   */
  export default {
    name: 'PersonalInfo',
    props: ["showStatus"],
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
