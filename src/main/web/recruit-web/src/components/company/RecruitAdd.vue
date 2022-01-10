<template>
  <div class="">
    <recruit-info ref="add">
      <el-form-item class="register-buttom">
        <el-button type="info" style="background: #5dd5c8;" @click="create">立刻创建</el-button>
        <el-button type="info" @click="reset">重置</el-button>
      </el-form-item>
    </recruit-info>
  </div>
</template>

<script>
  import RecruitInfo from './RecruitInfo.vue'
  import {
    mapState,
  } from 'vuex'
  /**
   * description
   */
  export default {
    name: 'RecruitAdd',
    components: {
      RecruitInfo,
    },
    computed: {
      ...mapState(['position', 'area', 'industry'])
    },
    data() {
      return {

      }
    },
    methods: {
      // 新增模块 重置表单
      reset() {
        this.$refs.add.reset()
      },
      // 创建招聘信息
      create() {
        let data = this.$refs.add.getData()
        if (!data) {
          return
        }
        this.$axios.addRecruit(data).then(res => {
          this.$message({
            message: '添加成功',
            type: 'success'
          });
          this.reset()
        })
      },
    },
  }
</script>

<style scoped>

</style>
