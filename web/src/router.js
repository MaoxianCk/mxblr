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
          path: '/admininstrument',
          component: resolve => require(['./views/admin/Instrument.vue'], resolve)
        },
        {
          path: '/adminarticleList',
          component: resolve => require(['./views/admin/ArticleList.vue'], resolve)
        },
        {
          path: '/adminarticleEdit',
          component: resolve => require(['./views/admin/ArticleEdit.vue'], resolve)
        },
        {
          path: '/admintag',
          component: resolve => require(['./views/admin/Tag.vue'], resolve),
        },
        {
          path: '/CommentList',
          component: resolve => require(['./views/admin/CommentList.vue'], resolve),
        },
        {
          path: '/adminuserList',
          component: resolve => require(['./views/admin/UserList.vue'], resolve),
        },
        {
          path: '/adminImageList',
          component: resolve => require(['./views/admin/imageList.vue'], resolve),
        }
      ]
    },
    {
      path: '/adminlogin',
      component: resolve => require(['./views/admin/Login.vue'], resolve)
    }
  ]
})
