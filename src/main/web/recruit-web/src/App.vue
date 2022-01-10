<template>
  <div id="app">
    <transition name="el-zoom-in-top">
      <top-nav class="nav" v-show="navShow"></top-nav>
    </transition>

    <div :class="{'show-nav': navShow, 'not-show-nav' : !navShow}">
      <keep-alive exclude="Info">
        <router-view />
      </keep-alive>
    </div>

  </div>
</template>

<script>
  import {
    mapState,
    mapMutations
  } from 'vuex'

  import TopNav from './components/Nav'

  export default {
    name: 'App',
    computed: {
      ...mapState(['navShow'])
    },
    components: {
      TopNav
    },
    data() {
      return {}
    },
    methods: {
      ...mapMutations({
        changePosition: 'changePosition',
        changeArea: 'changeArea',
        changeIndustry: 'changeIndustry'
      }),
      getSelect() {
        this.$axios.queryTreePosition().then(res => {
          this.changePosition(res.data)
        })
        this.$axios.queryTreeArea().then(res => {
          this.changeArea(res.data)
        })
        this.$axios.queryTreeIndustry().then(res => {
          this.changeIndustry(res.data)
        })
      },
    },
    created() {
      //  加载全局字典
      this.getSelect()
    },

  }
</script>

<style>
  * {
    margin: 0;
    padding: 0;
  }

  html,
  body,
  #app {
    height: 100%;
    min-width: 1200px;
    background: #f6f6f8;
  }

  .nav {
    position: absolute;
  }

  .fr-box.fr-basic .fr-element {
    min-height: 300px;
  }

  .show-nav {
    padding-top: 50px;
    /* height: 100%; */
  }

  .not-show-nav {
    height: 100%;
  }

  .el-menu--horizontal>.el-submenu .el-submenu__title {
    height: 50px;
    line-height: 50px;
  }

  .search-div .el-input__inner {
    height: 51.6px;
    line-height: 50px;
  }

  .subject {
    color: white;
    background: #5dd5c8;
  }

  .search-div .el-input-group__append {
    background-color: #5dd5c8;
    color: white;
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #5dd5c8;
  }

  .fr-box.fr-basic .fr-element {
    padding: 16px 24px !important;
  }

  .el-form-item__content {
    /* line-height: normal !important; */
    font-size: auto !important;
  }

  .my-blck {
    float: right !important;
  }

  .el-pagination.is-background .el-pager li:not(.disabled).active {
    background-color: #62d5c8;
  }

  /*  .fr-wrapper>div[style*="z-index: 9999"] {
    position: absolute;
    top: -10000px;
    opacity: 0;
  }

  .fr-element.fr-view {
    position: absolute;
    top: 0;
  }

  .fr-placeholder {
    margin-top: 0 !important;
  } */
</style>
