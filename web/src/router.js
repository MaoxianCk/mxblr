import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '*',
      redirect: '/'
    },
    {
      path: '/',
      component: resolve => require(['./views/index.vue'], resolve)
    },
    {
      path: '/article',
      component: resolve => require(['./views/article.vue'], resolve)
    },
    {
      path: '/admin',
      component: resolve => require(['./components/admin/Home.vue'], resolve),
      children: [
        {
          path: '/instrument',
          component: resolve => require(['./views/admin/Instrument.vue'], resolve)
        },
        {
          path:'/articleList',
          component: resolve => require(['./views/admin/ArticleList.vue'],resolve)
        }
      ]
    },
    {
      path: '/login',
      component: resolve => require(['./views/admin/Login.vue'], resolve)
    }
  ]
})
