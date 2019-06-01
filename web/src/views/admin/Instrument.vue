<template>
  <p>仪表盘</p>
</template>

<script>
export default {
  data () {
    return {
      userId: ''
    }
  },
  created () {
    this.userId = sessionStorage.getItem('userId');
  },
  computed: {

  },
  methods: {

  }
}

</script>

<style scoped>
</style>
