<template>
  <div class="">

    <!-- 搜索框 -->
    <el-form :inline="true">
      <el-form-item label="岗位名称:">
        <el-input v-model="search.positionName" placeholder="岗位名称" clearable></el-input>
      </el-form-item>

      <el-form-item label="状态:">
        <el-select v-model="search.status" placeholder="请选择人员规模">
          <el-option value="0" label="移除"></el-option>
          <el-option value="1" label="正常"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" style="background: #5dd5c8;" @click="handleSearch">查询</el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table :data="tableData" style="width: 100%" v-loading="loading">

      <el-table-column fixed prop="createDate" label="求职日期" width="150">
      </el-table-column>

      <el-table-column prop="positionName" label="岗位">
      </el-table-column>

      <el-table-column prop="userName" label="求职者" width="150" align="center">
      </el-table-column>

      <el-table-column fixed="right" label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleDetail(scope.row)">详情</el-button>

          <el-button size="mini" type="warning" v-if="scope.row.status == 1" @click="editStataus(scope.row, 0)">移除</el-button>
          <el-button size="mini" type="success" v-if="scope.row.status == 0" @click="editStataus(scope.row, 1)">恢复</el-button>
        </template>
      </el-table-column>

    </el-table>

    <el-pagination @current-change="handleCurrentChange" background layout="prev, pager, next" :total="total"
      :page-size="rows" style="margin: 15px;">
    </el-pagination>

    <!-- 修改页 -->
    <el-dialog title="求职信息" :visible.sync="dialogVisible" width="50%">
      <personal-info ref="personalInfo"></personal-info>
    </el-dialog>

  </div>
</template>

<script>
  import personalInfo from '@/common/info/PersonalInfo'
  /**
   * description
   */
  export default {
    name: 'DeliveryTable',
    components: {
      personalInfo
    },
    data() {
      return {
        loading: true,
        dialogVisible: false,
        tableData: [],
        page: 1,
        rows: 5,
        total: 0,
        search: {
          positionName: '',
          status: '1'
        },
      }
    },
    methods: {
      getTable() {
        this.loading = true
        let param = { ...this.search
        }
        param.page = this.page
        param.rows = this.rows
        this.$axios.getDeliveryByCompany(param).then(res => {
          this.tableData = res.data.data
          this.total = res.data.total
          this.loading = false
        })
      },
      handleSearch() {
        this.page = 1
        this.getTable()
      },
      handleCurrentChange(val) {
        this.page = val
        this.getTable()
      },
      handleDetail(data) {
        this.$axios.getPersonalInfo(data.userId).then(res => {
          console.log(res.data);
          this.dialogVisible = true
          this.$nextTick(() => {
            this.$refs.personalInfo.data = res.data
          })
        })

      },
      editStataus(data, status) {

        let msg = '此操作将' + (status == 1 ? '恢复' : '移除') + '该求职信息, 是否继续?'
        this.$confirm(msg, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          let param = {}
          param.id = data.id
          param.status = status
          this.$axios.editDelivery(param).then(res => {
            this.$message({
              type: 'success',
              message: '操作成功!'
            });
            this.getTable()
          })
        })

      }
    },
    created() {
      this.getTable()
    }
  }
</script>

<style scoped>

</style>
