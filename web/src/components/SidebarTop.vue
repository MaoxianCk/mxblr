<template>
  <div class="sidebar">
    <el-menu class="sidebar-el-menu"
             :default-active="onRoutes"
             :collapse="collapse"
             background-color="#324157"
             text-color="#bfcbd9"
             active-text-color="#20a0ff"
             unique-opened
             router>
      <template v-for="item in items">
        <template v-if="item.subs">
          <el-submenu :index="item.index"
                      :key="item.index">
            <template slot="title">
              <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
            </template>
            <template v-for="subItem in item.subs">
              <el-submenu v-if="subItem.subs"
                          :index="subItem.index"
                          :key="subItem.index">
                <template slot="title">{{ subItem.title }}</template>
                <el-menu-item v-for="(threeItem,i) in subItem.subs"
                              :key="i"
                              :index="threeItem.index">
                  {{ threeItem.title }}
                </el-menu-item>
              </el-submenu>
              <el-menu-item v-else
                            :index="subItem.index"
                            :key="subItem.index">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ subItem.title }}
              </el-menu-item>
            </template>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :index="item.index"
                        :key="item.index">
            <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
import bus from '../assets/js/bus.js';
export default {
  data () {
    return {
      collapse: false,
      items: [
        {
          icon: 'el-icon-tzj-chart',
          index: 'transactionrecordschool',
          title: '财务管理'
        },
        {
          icon: 'el-icon-tzj-people',
          index: '3',
          title: '人员管理',
          subs: [
            {
              icon: 'el-icon-tzj-people',
              index: 'parent',
              title: '学生管理'
            },
            {
              index: 'class',
              title: '班级管理'
            },
            {
              index: 'teacher',
              title: '教师管理'
            }
          ]
        },
        {
          icon: 'el-icon-tzj-date',
          index: 'schedule',
          title: '日程管理',
          subs: [
            {
              icon: 'el-icon-tzj-date',
              index: 'schedule',
              title: '日程管理'
            },
            {
              icon: 'el-icon-tzj-semester',
              index: 'termRecord',
              title: '学期管理'
            },
          ]
        },
        {
          icon: 'el-icon-tzj-class',
          index: 'course',
          title: '课程管理',
          subs: [
            {
              icon: 'el-icon-tzj-class',
              index: 'course',
              title: '课程管理'
            }, {
              icon: 'el-icon-tzj-active',
              index: 'activity',
              title: '活动管理'
            },
            {
              icon: 'el-icon-tzj-project',
              index: 'fixedCourse',
              title: '固定项目管理'
            }

          ]
        },
        {
          icon: 'el-icon-tzj-menu',
          index: 'dish',
          title: '菜谱管理',
          subs: [{
            index: 'dishes',
            title: '菜谱管理'
          }, {
            index: 'voDishesDetial',
            title: '菜品管理'
          }]
        },
        {
          icon: 'el-icon-tzj-proxy',
          index: 'agent',
          title: '机构管理',
          subs: [{
            index: 'BranchStructure',
            title: '分支机构管理'
          }, {
            index: 'agents',
            title: '代理机构管理'
          }]
        },
        {
          icon: 'el-icon-tzj-admin',
          index: 'adminManage',
          title: '管理员管理'
        }
      ]
    }
  },
  computed: {
    onRoutes () {
      return this.$route.path.replace('/', '');
    }
  },
  created () {
    // 通过 Event Bus 进行组件间通信，来折叠侧边栏
    bus.$on('collapse', msg => {
      this.collapse = msg;
    })
  }
}
</script>

<style scoped>
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 70px;
  bottom: 0;
  overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
  width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
  width: 250px;
}
.sidebar > ul {
  height: 100%;
}
</style>
