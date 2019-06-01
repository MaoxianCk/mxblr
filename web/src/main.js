import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css' // 默认主题
import './assets/css/icon.css'
import './assets/js/directives'
import 'babel-polyfill'

Vue.config.productionTip = false
Vue.use(ElementUI, {
  size: 'small'
})
Vue.prototype.$axios = axios

//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
  const role = sessionStorage.getItem('role')
  const userId = sessionStorage.getItem('userId')
  if (role != null && userId != null && to.path === '/login') {
    next('/instrument')
  }
  if (to.path === '/' || to.path === '/article') {
    next()
    return
  }
  if (role == null && to.path !== '/login') {
    next('/login')
  } else if (to.meta.permission) {
    // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
    role === '0' ? next() : next('/')
  } else {
    next()
  }
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
