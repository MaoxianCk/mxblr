<template>
  <div class="wrapper">
    <v-head></v-head>
    <t-sidebar v-if="role==0"></t-sidebar>
    <m-sidebar v-if="role==1||role==3"></m-sidebar>
    <v-sidebar v-if="role==2||role==4"></v-sidebar>
    <div class="content-box"
         :class="{'content-collapse':collapse}">
      <v-tags></v-tags>
      <div class="content">
        <transition name="move"
                    mode="out-in">
          <keep-alive :include="tagsList">
            <router-view></router-view>
          </keep-alive>
        </transition>
      </div>
    </div>
  </div>
</template>

<script>
import vHead from './Header.vue';
import vSidebar from './SidebarLow.vue';
import tSidebar from './SidebarTop.vue';
import mSidebar from './SidebarMid.vue';
import vTags from './Tags.vue';
import bus from '../assets/js/bus';
export default {
  data () {
    return {
      tagsList: [],
      collapse: false,
      role: ''
    }
  },
  components: {
    vHead, vSidebar, tSidebar, mSidebar, vTags
  },
  created () {
    this.role = sessionStorage.getItem('role');
    bus.$on('collapse', msg => {
      this.collapse = msg;
    })

    // 只有在标签页列表里的页面才使用keep-alive，即关闭标签之后就不保存到内存中了。
    bus.$on('tags', msg => {
      let arr = [];
      for (let i = 0, len = msg.length; i < len; i++) {
        msg[i].name && arr.push(msg[i].name);
      }
      this.tagsList = arr;
    })
  }
}
</script>
