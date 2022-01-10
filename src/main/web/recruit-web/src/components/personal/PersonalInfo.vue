<template>
  <div class="">
    <el-form label-position="left" label-width="75px" style="width: 75%;">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="就业状态:">
            <el-select v-model="editData.status" placeholder="请选择就业状态">
              <el-option :value="0" label="待业"></el-option>
              <el-option :value="1" label="已就业"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="公司:" v-show="editData.status == 1">
            <el-input v-model="editData.company" prefix-icon="el-icon-school" class="login-subject-input" placeholder="就业公司"></el-input>
          </el-form-item>
        </el-col>
      </el-row>


      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="性别:">
            <el-radio-group v-model="editData.sex">
              <el-radio :label="1">男</el-radio>
              <el-radio :label="0">女</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="出生年月:">
            <el-date-picker v-model="editData.birthday" type="month" value-format="yyyy-MM" placeholder="选择月">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="学校:">
            <el-input v-model="editData.school" prefix-icon="el-icon-school" class="login-subject-input" placeholder="请输入你的所在学校"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="学历:">
            <el-select v-model="editData.education" placeholder="请选择学历信息">
              <el-option value="高中及以下"></el-option>
              <el-option value="大专"></el-option>
              <el-option value="本科"></el-option>
              <el-option value="硕士"></el-option>
      <el-option value="博士及以上"></el-option>
      </el-select>
      </el-form-item>
      </el-col>
      </el-row>




      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="专业:">
            <el-input v-model="editData.major" prefix-icon="el-icon-copy-document" class="login-subject-input"
              placeholder="请输入你的所学专业"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="就读时间:">
            <el-date-picker v-model="editData.studyDate" type="monthrange" value-format="yyyy" range-separator="至"
              start-placeholder="开始时间" end-placeholder="结束时间">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>


      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="预期岗位:">
            <el-cascader v-model="editData.position" :options="position" :show-all-levels="false" placeholder="搜索"
              filterable :props="{ expandTrigger: 'hover',value: 'name',label: 'name' }"></el-cascader>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预期薪资:">
            <el-input-number v-model="editData.waves" :step="1" :precision="0" :max="100" :min="0" controls-position="right"></el-input-number>
            <span style="margin-left: 15px;">K</span>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="个人介绍:">
        <rich-text :height="150" :size="'mini'" ref="desc" style="width: 830px;"></rich-text>
      </el-form-item>

      <el-form-item class="register-buttom">
        <el-button type="info" style="background: #5dd5c8;" @click="editUserInfo">修改</el-button>
        <el-button type="info" @click="resetUserInfo">重置</el-button>
        <el-button type="warning" @click="dialogVisible = true" style="float: right;">上传简历</el-button>
      </el-form-item>

      <el-dialog title="求职信息" :visible.sync="dialogVisible" width="40%" :modal='false'>

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
  import RichText from '@/common/richText/RichText.vue'
  import {
    mapState,
  } from 'vuex'
  /**
   * description
   */
  export default {
    name: 'PersonalInfo',
    components: {
      RichText
    },
    computed: {
      ...mapState(['position', 'area', 'industry'])
    },
    data() {
      return {
        uploadUrl: this.$common.baseUrl + "upload/file",
        dialogVisible: false,
        data: {},
        editData: {},
        imageArr: ["image/png", "image/jpg", "image/gif", "image/jpeg", "image/bmp"],
      }
    },
    methods: {
      handleAvatarSuccess(res, file,fileList) {
        fileList.splice(0,fileList.length - 1)
        this.$message({
          type: 'success',
          message: '上传成功，点击修改保存该简历!'
        });
        this.editData.resumeUrl = res.link
        this.dialogVisible = false
      },
      beforeAvatarUpload(file) {

        return true;
      },
      editUserInfo() {
        if (this.editData.status == 1 && (this.editData.company == '' || this.editData.company == null)) {
          this.$message("已就业请填写 就业公司名称")
          return false
        }

        if (this.editData.birthday == '') {
          this.$message("请输出生年月")
          return false
        }
        if (this.editData.school == '') {
          this.$message("请输入所在学校")
          return false
        }
        if (this.editData.education == '') {
          this.$message("请选择学历")
          return false
        }
        if (this.editData.major == '') {
          this.$message("请输入所学专业")
          return false
        }
        if (this.editData.studyDate.length == 0) {
          this.$message("请选择就读时间")
          return false
        }
        if (this.editData.position.length == 0) {
          this.$message("请选择预期岗位")
          return false
        }
        if (this.editData.waves == '') {
          this.$message("请输入预期薪资")
          return false
        }

        if (this.editData.resumeUrl == '') {
          this.$message("该用户还未上传过简历，请先上传简历")
          return false
        }


        this.$confirm('此操作将修改个人信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          let param = { ...this.editData
          }
          param.describes = this.$refs.desc.froalaContent
          param.studyDate = param.studyDate.join("-")
          param.position = param.position.join("-")

          if (param.status == 0) {
            param.company = ''
          }
          console.log(param);
          this.$axios.editPersonal(param).then(res => {
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
            this.data = param
          })
        })
      },
      resetUserInfo() {
        this.editData = { ...this.data
        }
        this.editData.studyDate = this.editData.studyDate.split("-")
        this.editData.position = this.editData.position.split("-")
        this.$refs.desc.froalaContent = this.editData.describes
      },
      getUserInfo() {
        this.$axios.getUserInfo().then(res => {
          this.data = res.data
          this.resetUserInfo();
        })
      }
    },
    created() {
      this.getUserInfo()
    }
  }
</script>

<style scoped>

</style>
