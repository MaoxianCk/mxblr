<template>
  <div class="footer">
      <span class="copyright" v-for="(item,index) in str" :key="index" v-html="item">
        <!-- {{item}} -->
      </span>
  </div>
</template>
<script>
export default {
  data: function () {
    return {
      str:[
        "© 2019-2019 毛线球上的喵星人",
      ]
    }
  },
}
</script>

<style scoped>
.copyright{
  font-size: 12px;
  margin-right: 12px;
}
.footer {
  width: 100%;
  background-color: #111;
  color: rgb(109, 109, 109);
  text-align: center;
  line-height: 60px;
}
</style>