<template>
  <div class="">
    <!-- 搜索框 -->
    <el-form :inline="true">
      <el-form-item label="岗位名称:">
        <el-input v-model="search.positionName" placeholder="岗位名称" clearable></el-input>
      </el-form-item>

      <el-form-item label="类别:">
        <el-cascader v-model="search.position" :options="position" :show-all-levels="false" placeholder="搜索" clearable
          filterable :props="{ expandTrigger: 'hover',value: 'name',label: 'name',emitPath: false }"></el-cascader>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" style="background: #5dd5c8;" @click="handleSearch">查询</el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table :data="recruitData" style="width: 100%" v-loading="loading">

      <el-table-column fixed prop="createDate" label="创建日期" width="150">
      </el-table-column>

      <el-table-column prop="positionName" label="岗位名称">
      </el-table-column>

      <el-table-column prop="position" label="岗位类别" width="150" align="center">
      </el-table-column>

      <el-table-column label="薪资" width="150" align="center">
        <template slot-scope="scope">
          <span style="color: #fa6a43;font-weight: 800;">{{ scope.row.wavesBegin + '-' + scope.row.wavesEnd + 'k'}}</span>
        </template>
      </el-table-column>

      <el-table-column fixed="right" label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>

          <el-button size="mini" type="warning" v-if="scope.row.status == 1" @click="editStataus(scope.row, 0)">下架</el-button>
          <el-button size="mini" type="success" v-if="scope.row.status == 0" @click="editStataus(scope.row, 1)">上架</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @current-change="handleCurrentChange" background layout="prev, pager, next" :total="total"
      :page-size="rows" style="margin: 15px;">
    </el-pagination>

    <!-- 修改页 -->
    <el-dialog title="修改" :visible.sync="dialogVisible" width="980px">
      <recruit-info ref="edit">
      </recruit-info>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="edit">修 改</el-button>
      </span>
    </el-dialog>
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
    name: 'RecruitTable',
    components: {
      RecruitInfo,
    },
    computed: {
      ...mapState(['position', 'area', 'industry'])
    },
    data() {
      return {
        loading: true,
        dialogVisible: false,
        recruitData: [],
        page: 1,
        rows: 5,
        total: 0,
        search: {
          positionName: '',
          position: ''
        },
      }
    },
    methods: {
      handleSearch(){
        this.page = 1
        this.getTable()
      },
      // 获取表格信息
      getTable() {
        this.loading = true
        let param = { ...this.search
        }
        param.page = this.page
        param.rows = this.rows
        this.$axios.getRecruit(param).then(res => {
          this.recruitData = res.data.data
          this.total = res.data.total
          this.loading = false
        })
      },
      handleCurrentChange(val) {
        this.page = val
        this.getTable()
      },
      // 上下架
      editStataus(data, status) {

        let msg = '此操作将' + (status == 1 ? '上架' : '下架') + '该招聘信息, 是否继续?'
        this.$confirm(msg, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          let param = {}
          param.id = data.id
          param.status = status
          this.$axios.editRecruit(param).then(res => {
            this.$message({
              type: 'success',
              message: '操作成功!'
            });
            this.getTable()
          })
        })
      },
      // 点击修改按钮时触发
      handleEdit(data) {
        this.dialogVisible = true
        this.$nextTick(() => {
          this.$refs.edit.setData(data)
        })
      },
      // 修改招聘信息
      edit() {
        let data = this.$refs.edit.getData()
        if (!data) {
          return
        }
        this.$axios.editRecruit(data).then(res => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          });
          this.dialogVisible = false
          this.getTable()
        })
      },
    },
    created() {
      this.getTable()
    }
  }
</script>

<style scoped>

</style>
