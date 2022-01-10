<template>
  <div class="">
    <el-form label-position="left" label-width="75px" style="width: 60%;">
      <el-form-item label="公司全称:">
        <el-input v-model="editUserInfoData.fullName" prefix-icon="el-icon-info" class="login-subject-input"
          placeholder="请输入公司全称:"></el-input>
      </el-form-item>

      <el-form-item label="所属行业:">
        <el-cascader v-model="editUserInfoData.industry" :options="industry" placeholder="搜索" filterable :props="{ expandTrigger: 'hover',value: 'name',label: 'name' }"></el-cascader>
      </el-form-item>

      <el-form-item label="人员规模:">
        <el-select v-model="editUserInfoData.scale" placeholder="请选择人员规模">
          <el-option value="0-20人"></el-option>
          <el-option value="20-99人"></el-option>
          <el-option value="100-499人"></el-option>
          <el-option value="500-999人"></el-option>
          <el-option value="1000-9999人"></el-option>
          <el-option value="10000人以上"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="所在市区:">
        <el-cascader v-model="editUserInfoData.address" :options="area" placeholder="搜索" filterable :props="{ expandTrigger: 'hover',value: 'name',label: 'name' }"></el-cascader>
      </el-form-item>

      <el-form-item label="详细地址:">
        <el-input v-model="editUserInfoData.detailAddress" :rows="1" prefix-icon="el-icon-office-building" class="login-subject-input"
          placeholder="请输入公司详细地址"></el-input>
      </el-form-item>

      <el-form-item label="公司介绍:">
        <rich-text :height="150" :size="'mini'" ref="desc" style="width: 830px;"></rich-text>
      </el-form-item>

      <!-- <el-form-item class="register-buttom">
            <el-button type="info" style="background: #5dd5c8;" @click="breakPage(1)">上一步</el-button>
            <el-button type="info" style="background: #5dd5c8;" @click="nextPage()">完成</el-button>
          </el-form-item> -->
      <el-form-item class="register-buttom">
        <el-button type="info" style="background: #5dd5c8;" @click="editUserInfo">修改</el-button>
        <el-button type="info" @click="resetUserInfo">重置</el-button>
      </el-form-item>

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
    name: 'CompanyInfo',
    components: {
      RichText
    },
    computed: {
      ...mapState(['position', 'area', 'industry'])
    },
    data() {
      return {
        userInfoData: {},
        editUserInfoData: {},
      }
    },
    methods: {
      // 获取公司信息
      getUserInfo() {
        this.$axios.getUserInfo().then(res => {
          this.userInfoData = res.data
          this.resetUserInfo()
        })
      },
      // 重置公司数据
      resetUserInfo() {
        this.editUserInfoData = { ...this.userInfoData
        }
        this.editUserInfoData.address = this.editUserInfoData.address.split("-")
        this.editUserInfoData.industry = this.editUserInfoData.industry.split("-")
        this.$refs.desc.froalaContent = this.editUserInfoData.describes
      },
      // 修改公司数据
      editUserInfo() {
        if (this.editUserInfoData.fullName == '') {
          this.$message("请输入公司全称")
          return false
        }
        if (this.editUserInfoData.industry == '') {
          this.$message("请输选择所属行业")
          return false
        }
        if (this.editUserInfoData.scale == '') {
          this.$message("请输选择公司规模")
          return false
        }
        if (this.editUserInfoData.address == '') {
          this.$message("请输选择所在市区")
          return false
        }
        if (this.editUserInfoData.detailAddress == '') {
          this.$message("请输入公司公司详细地址")
          return false
        }

        this.$confirm('此操作将修改公司信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          let param = { ...this.editUserInfoData
          }
          param.describes = this.$refs.desc.froalaContent
          param.address = param.address.join("-")
          param.industry = param.industry.join("-")
          this.$axios.editCompany(param).then(res => {
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
            this.userInfoData = param
          })
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
