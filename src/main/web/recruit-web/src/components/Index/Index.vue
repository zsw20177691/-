<template>
  <div class="">
    <div class="banner">
      <div style="width: 1200px;margin: 0 auto;">
        <img src="../../assets/icon-sign-welcome.png" style="height: 120px;margin-left: 50px;" />
        <img src="../../assets/fast-reg-text.png" style="height: 26px;float: right;margin: 47px 0;" />
      </div>
    </div>


    <div style="margin: 40px auto;width: 935px;" class="search-div">
      <el-card class="box-card">
        <el-input placeholder="搜索职位、公司" v-model="search.keyword" class="input-with-select" style="height: 50px;line-height: 50px;">
          <el-button slot="append" icon="el-icon-search" @click="getRecruit"> 搜索</el-button>
        </el-input>
      </el-card>
    </div>

    <div class="recruit-list">


      <div>
        <el-form :inline="true">
          <el-form-item>
            <el-cascader v-model="search.industry" :options="industry" placeholder="选择行业" clearable filterable :props="{ expandTrigger: 'hover',value: 'name',label: 'name' }"></el-cascader>
          </el-form-item>

          <el-form-item>
            <el-cascader v-model="search.position" :options="position" placeholder="选择岗位" clearable filterable :props="{ expandTrigger: 'hover',value: 'name',label: 'name' }"></el-cascader>
          </el-form-item>

          <el-form-item>
            <el-cascader v-model="search.address" :options="area" placeholder="选择城市" clearable filterable :props="{ expandTrigger: 'hover',value: 'name',label: 'name' }"></el-cascader>
          </el-form-item>

          <el-form-item>
            <el-select v-model="search.scale" placeholder="公司规模" clearable>
              <el-option value="0-20人"></el-option>
              <el-option value="20-99人"></el-option>
              <el-option value="100-499人"></el-option>
              <el-option value="500-999人"></el-option>
              <el-option value="1000-9999人"></el-option>
              <el-option value="10000人以上"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-select v-model="search.waves" placeholder="薪资要求" clearable>
              <el-option label="3K以下" value="0-3"></el-option>
              <el-option label="3-5K" value="3-5"></el-option>
              <el-option label="5-10K" value="5-10"></el-option>
              <el-option label="10-15K" value="10-15"></el-option>
              <el-option label="15-20K" value="15-20"></el-option>
              <el-option label="20-30K" value="20-30"></el-option>
              <el-option label="30-50K" value="30-50"></el-option>
              <el-option label="50K以上" value="50-100"></el-option>
            </el-select>
          </el-form-item>

        </el-form>

      </div>

      <div class="recruit-list-top">最新职位</div>

      <el-row :gutter="20">
        <el-col v-for="item,i in recruitList" :key="item.id" :span="8">
          <div class="recruit-info" @click="goToInfo(item)">
            <span style="font-size: 15px;color: #414a60;">
              {{item.positionName}}
            </span>
            <span style="float: right;font-size: 15px;color: #fd7240;">
              {{ item.wavesBegin + '-' + item.wavesEnd + 'k'}}
            </span>
            <br />
            <span style="font-size: 13px;color: #8d92a1;">
              {{item.education}}
            </span>
            <div style="border-top: 1px solid #eef0f5;margin-top: 8px;padding-top: 12px;display: flex;">
              <img :src="item.avatar" style="width: 30px;height: 30px;display: flex;align-items: center;" />

              <span style="font-size: 13px;color: #8d92a1;display: flex;align-items: center;margin-left: 10px;">
                {{item.companyName}}
              </span>

              <span style="font-size: 13px;color: #61687c;display: flex;align-items: center;justify-content:flex-end;flex: 1;">
                <i class="el-icon-location-outline"></i>
                {{item.address}}
              </span>
            </div>

          </div>
        </el-col>
      </el-row>

      <el-pagination background layout="prev, pager, next" :total="total" :page-size="9" @current-change="handleCurrentChange"
        style="text-align: center;margin: 20px 0;"></el-pagination>
    </div>

  </div>
</template>

<script>
  import {
    mapState,
  } from 'vuex'
  /**
   * author
   * description  首页
   */
  export default {
    name: '',
    computed: {
      ...mapState(['position', 'area', 'industry'])
    },
    data() {
      return {
        recruitList: [],
        total: 0,
        page: 1,
        search: {
          keyword: '',
          industry: [],
          scale: '',
          address: [],
          position: [],
          waves: '',
        }
      }
    },
    methods: {
      getRecruit() {
        let param = { ...this.search
        }
        param.page = this.page
        param.industry = param.industry.join("-")
        param.address = param.address.join("-")
        param.position = param.position[2]
        if (param.waves != '') {
          let waves = param.waves.split("-")
          param.wavesBegin = waves[0]
          param.wavesEnd = waves[1]
        }

        this.$axios.getRecruitInfoList(param).then(res => {
          this.recruitList = res.data.data
          this.total = res.data.total
        })
      },
      handleCurrentChange(val) {
        this.page = val
        this.getRecruit()
      },
      goToInfo(param) {
        // console.log(param);
        this.$router.push({
          path: '/info',
          query: {
            id: param.id,
            companyName: param.companyName,
            avatar: param.avatar
          },
        });
      }
    },

    created() {
      this.getRecruit()
    }
  }
</script>

<style scoped>
  .banner {
    height: 120px;
    background: #5dd5c8;
  }

  .el-card__body {
    padding: 0;
  }

  .recruit-list {
    margin: 0 auto;
    width: 1200px;
  }

  .recruit-list-top {
    font-weight: 600;
    padding-left: 20px;
    height: 50px;
    border-left: #00c2b3 solid 3px;
    color: #00c2b3;
    background: #FFFFFF;
    line-height: 50px;
    margin-bottom: 15px;
  }

  .recruit-info {
    height: 98px;
    background: #FFFFFF;
    margin-bottom: 10px;
    padding: 16px 20px;
  }

  .recruit-info:hover {
    box-shadow: 5px 5px 20px rgba(0, 0, 0, .05);
  }
</style>
