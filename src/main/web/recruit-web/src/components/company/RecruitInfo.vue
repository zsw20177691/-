<template>
  <div class="">
    <el-form label-position="left" label-width="75px" style="width: 60%;">
      <el-form-item label="岗位:">
        <el-input v-model="recruitData.positionName" prefix-icon="el-icon-copy-document" class="login-subject-input"
          placeholder="请输入岗位全称"></el-input>
      </el-form-item>

      <el-form-item label="岗位类别:">
        <el-cascader v-model="recruitData.position" :options="position" :show-all-levels="false" placeholder="搜索"
          filterable :props="{ expandTrigger: 'hover',value: 'name',label: 'name',emitPath: false }"></el-cascader>
      </el-form-item>

      <el-form-item label="学历要求:">
        <el-select v-model="recruitData.education" placeholder="请选择学历要求">
          <el-option value="高中及以下"></el-option>
          <el-option value="大专"></el-option>
          <el-option value="本科"></el-option>
          <el-option value="硕士"></el-option>
          <el-option value="博士及以上"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="薪资:">
        <el-input-number v-model="recruitData.wavesBegin" :step="1" :precision="0" :max="100" :min="0"
          controls-position="right"></el-input-number>
        <span style="text-align: center;font-size: 18px;margin: 0 10px;">-</span>
        <el-input-number v-model="recruitData.wavesEnd" :step="1" :precision="0" :max="100" :min="0" controls-position="right"></el-input-number>
        <span style="margin-left: 15px;">K</span>
      </el-form-item>

      <el-form-item label="职位描述:">
        <rich-text :height="200" :size="'mini'" ref="richText" class="company-richTxt"></rich-text>
      </el-form-item>

      <!-- 按钮插槽 -->
      <slot></slot>

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
    name: 'RecruitInfo',
    components: {
      RichText
    },
    computed: {
      ...mapState(['position', 'area', 'industry'])
    },
    data() {
      return {
        recruitData: {
          position: '',
          education: '',
          positionName: '',
          wavesBegin: '',
          wavesEnd: '',
        }
      }
    },
    methods: {
      getData() {
        console.log(this.recruitData);
        if (this.recruitData.positionName == '') {
          this.$message("请输入岗位名称")
          return false
        }
        if (this.recruitData.position == '') {
          this.$message("请选择岗位")
          return false
        }
        if (this.recruitData.education == '') {
          this.$message("请选择学历")
          return false
        }
        if (this.recruitData.wavesBegin == '' || this.recruitData.wavesEnd == '') {
          this.$message("请输入薪资")
          return false
        }
        if (this.recruitData.wavesBegin > this.recruitData.wavesEnd) {
          this.$message("预期最低薪资需 小于 预期最高薪资")
          return false
        }

        let data = { ...this.recruitData
        }
        data.describes = this.$refs.richText.froalaContent
        return data
      },
      reset() {
        this.recruitData = {
          position: '',
          education: '',
          positionName: '',
          wavesBegin: '',
          wavesEnd: '',
        }
        this.$refs.richText.froalaContent = ''
      },
      setData(data) {
        this.recruitData = data
        this.$refs.richText.froalaContent = data.describes
      }

    },
  }
</script>

<style scoped>
  .company-richTxt {
    width: 830px;
  }
</style>
