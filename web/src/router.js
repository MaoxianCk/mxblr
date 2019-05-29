import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      redirect: '/transactionrecordschool',
    },
    {
      path: '/',
      component: resolve => require(['./components/Home.vue'], resolve),
      meta: { title: '自述文件' },
      children: [
        {
          path: '/transactionrecordschool',
          component: resolve =>
            require(['./views/TransactionRecordSchool.vue'], resolve),
          meta: { title: '机构列表' }
        },
        {
          path: '/transactionrecordall',
          component: resolve =>
            require(['./views/TransactionRecordAll.vue'], resolve),
          meta: { title: '班级总记录' }
        },
        {
          path: '/showalltransactionrecord',
          component: resolve =>
            require(['./views/ShowAllTransactionRecord.vue'], resolve),
          meta: { title: '所有交易记录' }
        },
        {
          path: '/transactionrecordclass',
          component: resolve =>
            require(['./views/TransactionRecordClass.vue'], resolve),
          meta: { title: '班级列表' }
        },
        {
          path: '/transactionrecordstudent',
          component: resolve =>
            require(['./views/TransactionRecordStudent.vue'], resolve),
          meta: { title: '学生列表' }
        },
        {
          path: '/transactionrecord',
          component: resolve =>
            require(['./views/TransactionRecord.vue'], resolve),
          meta: { title: '交易记录' }
        },
        {
          path: '/class',
          component: resolve =>
            require(['./views/Class.vue'], resolve),
          meta: { title: '班级管理' }
        },
        {
          path: '/schedule',
          component: resolve =>
            require(['./views/Schedule.vue'], resolve),
          meta: { title: '日程管理' }
        },
        {
          path: '/parent',
          component: resolve =>
            require(['./views/StudentClass.vue'], resolve),
          meta: { title: '班级选择' },
        },
        {
          path: '/studentclass',
          component: resolve =>
            require(['./views/StudentClass.vue'], resolve),
          meta: { title: '学生管理' }
        },
        {
          path: '/adminManage',
          component: resolve =>
            require(['./views/AdminManage.vue'], resolve),
          meta: { title: '管理员管理' }
        },
        {
          path: '/studentmanage',
          component: resolve =>
            require(['./views/StudentManage.vue'], resolve),
          meta: { title: '学生管理' }
        },
        {
          path: '/course',
          component: resolve =>
            require(['./views/Course.vue'], resolve),
          meta: { title: '课程管理' }
        },
        {
          path: '/activity',
          component: resolve => require(['./views/Activity.vue'], resolve),
          meta: { title: '活动管理' }
        },
        {
          path: '/fixedCourse',
          component: resolve => require(['./views/FixedCourse.vue'], resolve),
          meta: { title: '固定项目管理' }
        },
        {
          path: '/termRecord',
          component: resolve => require(['./views/TermRecord.vue'], resolve),
          meta: { title: '学期管理' }
        },
        {
          path: '/teacher',
          component: resolve =>
            require(['./views/Teacher.vue'], resolve),
          meta: { title: '教师管理' }
        },
        {
          path: '/dishes',
          component: resolve =>
            require(['./views/Dishes.vue'], resolve),
          meta: { title: '菜谱管理' }
        },
        {
          path: '/vodishesdetial',
          component: resolve =>
            require(['./views/voDishesDetial.vue'], resolve),
          meta: { title: '菜品管理' }
        },
        {
          path: '/agents',
          component: resolve =>
            require(['./views/Agents.vue'], resolve),
          meta: { title: '代理机构管理' }
        },
        {
          path: '/branchstructure',
          component: resolve =>
            require(['./views/BranchStructure.vue'], resolve),
          meta: { title: '分支机构管理' }
        },
        {
          path: '/contract',
          component: resolve =>
            require(['./views/Contract.vue'], resolve),
          meta: { title: '合约管理' }
        }

      ]
    },
    {
      path: '/login',
      component: resolve => require(['./views/Login.vue'], resolve)
    },
    {
      path: '*',
      redirect: '/404'
    }
  ]
})
