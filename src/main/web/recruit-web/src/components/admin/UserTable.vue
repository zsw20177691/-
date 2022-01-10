<template>
  <div class="">
    <!-- 搜索框 -->
    <el-form :inline="true">
      <el-form-item label="用户名:">
        <el-input v-model="search.name" placeholder="名称 手机号" clearable></el-input>
      </el-form-item>

      <el-form-item label="用户类型:">
        <el-select v-model="search.type" placeholder="选择用户类型" clearable>
          <el-option value="1" label="学生"></el-option>
          <el-option value="2" label="企业"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" style="background: #5dd5c8;" @click="handleSearch">查询</el-button>
      </el-form-item>
    </el-form>


    <!-- 表格 -->
    <el-table :data="tableData" style="width: 100%" v-loading="loading">

      <el-table-column label="用户名">
        <template slot-scope="scope">
          <el-avatar :src="scope.row.avatar" style="vertical-align:middle;margin-left: 30px;" size="small"></el-avatar>
          <span style="margin-left: 10px">{{ scope.row.name }}</span>
        </template>
      </el-table-column>

      <el-table-column prop="username" label="手机号" width="200">
      </el-table-column>

      <el-table-column label="类型" width="180" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" v-if="scope.row.type == 1">学生</el-button>
          <el-button size="mini" type="info" v-if="scope.row.type == 2">企业</el-button>
        </template>
      </el-table-column>

      <el-table-column fixed="right" label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleDetail(scope.row)">详情</el-button>

          <el-button size="mini" type="warning" v-if="scope.row.status == 1" @click="editStataus(scope.row, 0)">停用</el-button>
          <el-button size="mini" type="success" v-if="scope.row.status == 0" @click="editStataus(scope.row, 1)">恢复</el-button>
        </template>
      </el-table-column>

    </el-table>

    <el-pagination @current-change="handleCurrentChange" background layout="prev, pager, next" :total="total"
      :page-size="rows" style="margin: 15px;">
    </el-pagination>

    <!-- 修改页 -->
    <el-dialog title="学生信息" :visible.sync="dialogVisible" width="50%">
      <personal-info ref="personalInfo" :showStatus="true"></personal-info>
    </el-dialog>


    <!-- 修改页 -->
    <el-dialog title="企业信息" :visible.sync="dialogVisible2" width="50%">
      <company-info ref="companyInfo" :showStatus="true"></company-info>
    </el-dialog>
  </div>
</template>

<script>
  import personalInfo from '@/common/info/PersonalInfo'
  import CompanyInfo from '@/common/info/CompanyInfo'
  /**
   * description
   */
  export default {
    name: 'UserTable',
    components: {
      personalInfo,
      CompanyInfo
    },
    data() {
      return {
        loading: true,
        dialogVisible: false,
        dialogVisible2: false,
        tableData: [],
        page: 1,
        rows: 5,
        total: 0,
        search: {
          name: '',
          type: ''
        }
      }
    },
    methods: {
      handleDetail(data) {
        if(data.type == 1){
          this.$axios.getPersonalInfo(data.id).then(res => {
            this.dialogVisible = true
            this.$nextTick(() => {
              this.$refs.personalInfo.data = res.data
            })
          })
        }else{
          this.$axios.getCompanyInfo(data.id).then(res => {
            this.dialogVisible2 = true
            this.$nextTick(() => {
              this.$refs.companyInfo.data = res.data
            })
          })
        }


      },
      handleSearch() {
        this.page = 1
        this.getTable()
      },
      getTable() {
        this.loading = true
        let param = { ...this.search
        }
        param.page = this.page
        param.rows = this.rows
        this.$axios.getUsers(param).then(res => {
          this.tableData = res.data.data
          this.total = res.data.total
          this.loading = false
        })
      },
      handleCurrentChange(val) {
        this.page = val
        this.getTable()
      },
      editStataus(data, status) {
        let msg = '此操作将' + (status == 1 ? '冻结' : '恢复') + '该用户, 是否继续?'
        this.$confirm(msg, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          let param = {}
          param.id = data.id
          param.status = status
          this.$axios.editUserStatus(param).then(res => {
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
