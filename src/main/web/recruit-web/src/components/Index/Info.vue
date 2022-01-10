<template>
  <div class="">
    <div class="info-banner">
      <div class="info-banner-subject">

        <div style="display: inline-block;">
          <div class="name">
            <h1 v-text="data.recruit.positionName"></h1>
            <span v-text="data.recruit.wavesBegin + '-' +data.recruit.wavesEnd + 'k' "></span>
          </div>
          <p v-text="data.company.address+ ' · ' +data.recruit.position + ' · ' +data.recruit.education"></p>
          <p v-text="'发布于：' +data.recruit.createDate" style="font-size: 12px;"></p>
        </div>


        <div style="float: right;" v-if="user.type == 1 ">
          <a @click="delivery">
            投递简历
          </a>
        </div>
      </div>
    </div>


    <div class="info-subjcet">
      <div class="recruit-info">
        <span class="title">职位描述</span>

        <div class="fr-view" v-html="data.recruit.describes">

        </div>

        <span class="title">公司介绍</span>

        <div class="fr-view" v-html="data.company.describes">

        </div>
      </div>

      <div class="company-info">
        <p>公司基本信息</p>

        <div class="company-info-title">
          <img :src="info.avatar" />
          <span v-text="info.companyName"></span>
        </div>

        <div class="company-info-detail">
          <i class="el-icon-office-building"></i>
          <span v-text="data.company.fullName"></span>
        </div>

        <div class="company-info-detail">
          <i class="el-icon-s-operation"></i>
          <span v-text="data.company.industry"></span>
        </div>


        <div class="company-info-detail">
          <i class="el-icon-user"></i>
          <span v-text="data.company.scale"></span>
        </div>

        <div class="company-info-detail">
          <i class="el-icon-location-outline"></i>
          <span v-text="data.company.detailAddress"></span>
        </div>

      </div>


    </div>
  </div>
</template>

<script>
  import {
    mapState,
  } from 'vuex'

  /**
   * description   用户点击招聘信息后 跳转的详情页面
   */
  export default {
    name: 'Info',
    computed: {
      ...mapState(['user'])
    },
    data() {
      return {
        isDelivery: false,
        info: {},
        data: {
          recruit: {
            wavesBegin: '-',
            wavesEnd: '-',
            position: '-',
            education: '-',
            positionName: '-',
            createDate: '-'
          },
          company: {}
        }
      }
    },
    methods: {
      getRecruit() {
        this.info = this.$route.query
        this.$axios.getRecruitInfo(this.info.id).then(res => {
          this.data = res.data
        })
      },
      delivery(){
        if(this.isDelivery){
          this.$message('请勿反复投递该职位');
          return false;
        }

        let param = {}
        param.companyId = this.data.company.userId
        param.recruitId = this.data.recruit.id
        param.userName = this.user.name
        param.positionName = this.data.recruit.positionName
        this.$axios.addDelivery(param).then(res => {
          this.isDelivery = true
          this.$message({
            type: 'success',
            message: '投递成功!'
          });

        })
      }
    },
    created() {
      this.getRecruit()
    },

  }
</script>

<style scoped>
  .company-info .company-info-title {
    line-height: 60px;
    color: #414a60;
    font-size: 14px;
  }

  .company-info .company-info-detail {
    margin-top: 30px;
    line-height: 20px;
    height: 20px;
    color: #414a60;
    font-size: 14px;
  }

  .company-info .company-info-detail i {
    margin-right: 15px;
  }

  .info-subjcet .company-info {
    float: left;
    width: 280px;
    padding: 25px;
  }

  .company-info img {
    width: 60px;
    height: 60px;
    float: left;
    margin-right: 18px;
    border-radius: 10px;
  }

  .info-subjcet .company-info p {
    line-height: 25px;
    margin-bottom: 25px;
    font-size: 15px;
    font-weight: 500;
    color: #414a60;
  }

  .info-subjcet {
    width: 1200px;
    margin: 30px auto;
    padding-bottom: 50px;
  }

  .info-subjcet .recruit-info {
    width: 835px;
    padding-right: 25px;
    border-right: 1px #e6e6e7 solid;
    float: left;
    padding-bottom: 50px;
  }

  .info-subjcet .fr-view {
    margin-top: 15px;
    color: #61687c;
    line-height: 36px;
  }

  .info-subjcet .title {
    display: inline-block;
    height: 25px;
    font-weight: 700;
    font-size: 15px;
    color: #424a5e;
    border-bottom: 3px solid #5dd5c8;
  }

  .info-banner {
    background: #444c60 url(../../assets/detail-bg.jpg) center center no-repeat;
    height: 140px;
    padding: 24px 0;
  }

  .info-banner-subject {
    width: 1200px;
    margin: 0 auto;
    height: 140px;

  }

  .info-banner-subject .name {
    font-size: 32px;
    line-height: 45px;
    padding: 11px 0 8px;
    color: #fff;
    font-weight: 400;
  }

  .info-banner-subject h1 {
    vertical-align: middle;
    font-size: 32px;
    max-width: 460px;
    white-space: nowrap;
    text-overflow: ellipsis;
    display: inline-block;
    font-weight: 400;
    margin-right: 10px;
    line-height: 35px;
    overflow: hidden;
    color: #fff;
  }

  .info-banner-subject .name span {
    display: inline-block;
    vertical-align: middle;
    color: #fa6a43;
    height: 42px;
    font-size: 30px;
    line-height: 42px;
    font-weight: 600;
  }

  .info-banner-subject p {
    color: rgba(255, 255, 255, .7);
    margin-bottom: 10px;
  }

  .info-banner-subject a {
    width: 139px;
    background: 0 0;
    display: inline-block;
    height: 45px;
    line-height: 45px;
    color: #5dd5c8;
    border: 1px #5dd5c8 solid;
    text-align: center;
    cursor: pointer;
    margin-top: 20px;
  }

  .info-banner-subject a:hover{
    color: #FFF;
  }

</style>
