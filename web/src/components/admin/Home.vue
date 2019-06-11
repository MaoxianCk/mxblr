<template>
  <div class="wrapper">
    <v-head></v-head>
    <v-sidebar v-if="role==0"></v-sidebar>
    <m-sidebar v-else></m-sidebar>
    <div class="content-box"
         :class="{'content-collapse':collapse}">
      <div class="content">
        <transition name="move"
                    mode="out-in">
            <router-view></router-view>
        </transition>
      </div>
    </div>
  </div>
</template>

<script>
import vHead from './Header.vue';
import vSidebar from './Sidebar.vue';
import mSidebar from './SideBar-low.vue';
import bus from '../../assets/js/bus';
export default {
  data () {
    return {
      collapse: false,
      role: ''
    }
  },
  components: {
    vHead, vSidebar,mSidebar
  },
  created () {
    this.role = sessionStorage.getItem('role');
    bus.$on('collapse', msg => {
      this.collapse = msg;
    })
  }
}
</script>
