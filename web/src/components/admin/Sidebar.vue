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
import bus from '../../assets/js/bus.js';
export default {
  data () {
    return {
      collapse: false,
      items: [
        // {
        //   icon: 'el-icon-mxblr-instrument',
        //   index: 'admininstrument',
        //   title: '仪表盘'
        // },
        {
          icon: 'el-icon-mxblr-article',
          index: '2',
          title: '文章管理',
          subs: [
            {
              index: 'adminarticleList',
              title: '文章列表',
            },
            {
              index: 'adminarticleEdit',
              title: '写文章',
            },
            {
              index: 'admintag',
              title: '分类管理',
            },
            {
              index: 'CommentList',
              title: '评论管理',
            }
          ]
        },
        {
          icon: 'el-icon-mxblr-people',
          index: '3',
          title: '用户管理',
          subs: [
            {
              index: 'adminuserList',
              title: '用户列表',
            }
          ]
        },
        {
          icon: 'el-icon-mxblr-image',
          index: '4',
          title: '图片管理',
          subs: [
            {
              index: 'adminImageList',
              title: '图片列表',
            }
          ]
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
