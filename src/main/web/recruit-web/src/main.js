// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'

// elemt-ui 引入
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

// 引入Froala富文本编辑器
require('froala-editor/js/froala_editor.pkgd.min')
require('froala-editor/css/froala_editor.pkgd.min.css')
require('froala-editor/js/languages/zh_cn')
require('font-awesome/css/font-awesome.css')
require('froala-editor/css/froala_style.min.css')
// require('froala-editor/js/plugins.pkgd.min.js')
// require('froala-editor/js/plugins.pkgd.min.js')

import jQuery from 'jquery'
import VueFroala from 'vue-froala-wysiwyg'
window.$ = jQuery
Vue.use(VueFroala)

// 全局挂载通用属性、方法
import common from './common/common.js'
Vue.prototype.$common = common

// 全局挂载 request中的请求
import * as request from './request'
Vue.prototype.$axios = request

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
