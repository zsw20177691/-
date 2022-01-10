<template>
  <div>
    <el-form :inline="true" >
      <el-form-item label="就业公司名称">
        <el-input v-model="workdata.company" prefix-icon="el-icon-school" ></el-input>
      </el-form-item>
      <el-form-item label="就业岗位名称">
        <el-input v-model="workdata.jobs" prefix-icon="el-icon-school" ></el-input>
      </el-form-item>
      <el-form-item label="资薪">
        <el-input v-model="workdata.salary" prefix-icon="el-icon-school" ></el-input>
      </el-form-item>
      <el-form-item label="公司所在城市">
        <el-input v-model="workdata.companyCity" prefix-icon="el-icon-school" ></el-input>
      </el-form-item>
      <el-form-item label="公式详细地址">
        <el-input v-model="workdata.detailedAddress" prefix-icon="el-icon-school" ></el-input>
      </el-form-item>
      <el-form-item label="工作行业">
        <el-input v-model="workdata.industrey" prefix-icon="el-icon-school" ></el-input>
      </el-form-item>
      <el-form-item label="三方协议编号">
        <el-input v-model="workdata.threeNumber" prefix-icon="el-icon-school" ></el-input>
      </el-form-item>
      <el-form-item class="register-buttom">
        <el-button type="info" style="background: #5dd5c8;" @click="preservation">保存</el-button>
        <el-button type="info" @click="editInfo">修改</el-button>
        <el-button type="info" style="background: #5dd5c0;" @click="getWorkInfo">点击获取就业信息</el-button>
        <el-button type="warning" @click="dialogVisible = true" style="float: right;">上传三方协议图片文件夹</el-button>
      </el-form-item>
      <el-dialog title="上传三方协议图片" :visible.sync="dialogVisible" width="40%" :modal='false'>
        <el-upload class="upload-demo" drag :action="uploadUrl" style="" align="center" :before-upload="beforeAvatarUpload"
                   :on-success="handleAvatarSuccess">
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        </el-upload>
      </el-dialog>
    </el-form>
  </div>

</template>

<script>
    export default {
        name: "work_Info",
      data(){
          return{
            workdata:{
              company:"",
              jobs:"",
              salary:"",
              companyCity:"",
              detailedAddress:"",
              industrey:"",
              threeNumber:"",
            },
            dialogVisible: false,
            uploadUrl: this.$common.baseUrl + "upload/file",
        }
      },
      methods:{
        getWorkInfo() {
          let param = { ...this.workdata
          }
          this.$axios.getWorkInfo(param).then(res => {
            this.workdata = res.data
          })
        },
        handleAvatarSuccess(res, file,fileList) {
          fileList.splice(0,fileList.length - 1)
          this.$message({
            type: 'success',
            message: '上传成功，点击修改保存该文件!'
          });
          this.workdata.resumeUrl = res.link
          this.dialogVisible = false
        },
        beforeAvatarUpload(file) {
          return true;
        },
        preservation(){
          if (this.workdata.company == '') {
            this.$message("请输入就业公司")
            return false
          }
          if (this.workdata.jobs == '') {
            this.$message("请输入工作岗位")
            return false
          }
          if (this.workdata.salary == '') {
            this.$message("请输入资薪")
            return false
          }
          if (this.workdata.company_City == '') {
            this.$message("请输入工作城市")
            return falseb
          }
          if (this.workdata.detailed_Address == '') {
            this.$message("请输入公司详细地址")
            return false
          }
          if (this.workdata.industrey == '') {
            this.$message("请输入工作行业")
            return false
          }
          if (this.workdata.three_Number == '') {
            this.$message("请输入三方协议编号")
            return false
          }
          if (this.workdata.resumeUrl == '') {
            this.$message("请上传三方协议照片")
            return false
          }
          let data = { ...this.workdata
          }
          console.log(data);
          this.$axios.presservation(data).then(res => {
            this.$message({
              message: '保存成功',
              type: 'success'
            });
            this.workdata = data
          })
        },
        editInfo(){
          if (this.workdata.company == '') {
            this.$message("请输入就业公司")
            return false
          }
          if (this.workdata.jobs == '') {
            this.$message("请输入工作岗位")
            return false
          }
          if (this.workdata.salary == '') {
            this.$message("请输入资薪")
            return false
          }
          if (this.workdata.company_City == '') {
            this.$message("请输入工作城市")
            return false
          }
          if (this.workdata.detailed_Address == '') {
            this.$message("请输入公司详细地址")
            return false
          }
          if (this.workdata.industrey == '') {
            this.$message("请输入工作行业")
            return false
          }
          if (this.workdata.three_Number == '') {
            this.$message("请输入三方协议编号")
            return false
          }
          if (this.workdata.resumeUrl == '') {
            this.$message("请上传三方协议照片")
            return false
          }
          let param = { ...this.workdata
          }
          console.log(param);
          this.$axios.editpresservation(param).then(res => {
            this.$message({
              message: '保存成功',
              type: 'success'
            });
            this.workdata = param;
          })
        },
        created() {
          this.getWorkInfo()
        }
      }
    }
</script>

<style scoped>

  .register-buttom {
    position: absolute;
    bottom: 35%;
  }
</style>
