import Vue from 'vue'
import Router from 'vue-router'
import store from '../store'

Vue.use(Router)


let router = new Router({
  routes: [{
      path: '/',
      name: 'Index', // 主页
      component: () => import('@/components/index/Index')
    },
    {
      path: '/info',
      name: 'Info', // 招聘信息详情页
      component: () => import('@/components/index/Info')
    },
    {
      path: '/login',
      name: 'Login', // 登录页
      component: () => import('@/components/login/Login')
    },
    {
      path: '/register',
      name: 'Register', // 注册页
      component: () => import('@/components/register/Register')
    },
    {
      path: '/admin',
      name: 'Admin', // 管理员页面
      component: () => import('@/components/admin/Admin')
    },
    {
      path: '/company',
      name: 'Company', // 公司信息页
      component: () => import('@/components/company/Company')
    },
    {
      path: '/personal',
      name: 'Personal', // 个人中心页
      component: () => import('@/components/personal/Personal')
    }
  ]
})
export default router

// 路由拦截, 控制nav的显示 和 权限校验
router.beforeEach((to, from, next) => {

  // 登录的用户 前往登录界面直接跳转主界面
  if (to.path == '/login' && store.state.user.id != null) {
    next({
      path: '/'
    })

    // 登录页 和注册页关闭 nav
  } else if (to.path == '/login' || to.path == '/register') {
    store.commit('changeNavShow', false)
    next()

    // 非企业用户前往 企业界面跳转主页
  } else if (to.path == '/company' && store.state.user.type != 2) {
    next({
      path: '/'
    })

    // 非admin用户前往 admin界面跳转主页
  } else if (to.path == '/admin' && store.state.user.type != 0) {
    next({
      path: '/'
    })

    // 非login界面直接默认打开nav
  } else if (to.path == '/personal' && store.state.user.type != 1) {
    next({
      path: '/'
    })

    // 非login界面直接默认打开nav
  }else {
    store.commit('changeNavShow', true)
    next()
  }

})
