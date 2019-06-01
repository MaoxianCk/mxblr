<template>
  <div>
    <div class="header">
      <div class="headerTitle">
        <h1>{{article.articleInfo.title}}</h1>
      </div>
    </div>
    <div class="textContainer">
      <div v-html="article.content"></div>
    </div>
  </div>
</template>
<script>
export default {
  data: function () {
    return {
      URL_DEFINE_ROOT: '/local/',
      articleId: null,
      article: null,
    }
  },
  methods: {
    getArticle () {
      let that = this;
      this.articleId = this.$route.query.id;
      if (this.articleId != null) {
        this.$axios.get(this.URL_DEFINE_ROOT + '/user/article/getArticleById', { params: { articleId: this.articleId } }).then(function (res) {
          if (res.data.status == "success") {
            that.article = res.data.data;
            console.clear();
          } else {
            that.$message.error(res.data.data.errMsg);
          }
        }).catch(function (err) {
          console.log(err);
        })
      }
    }
  },
  created () {
    this.getArticle();
  }
}
</script>
<style scope>
.header {
  top: 0;
  height: 560px;
  background-image: url("../assets/img/background.jpg");
  background-position-x: 50%;
  background-size: cover;
  text-align: center;
}
.headerTitle {
  position: relative;
  top: 150px;
  width: 100%;
  margin: 0 auto;
  text-align: center;
  font-size: 37px;
  color: #fff;
  letter-spacing:8px;
}
.textContainer {
  background-color: #fff;
  width: 700px;
  min-height: 300px;
  margin: 50px auto;
  color: #333;
  font-size: 16px;
  line-height: 1.75;
  font-family: Hiragino Sans GB, MicroSoft YaHei;
}
.textContainer h1,h2 {
  margin-top: 32px;
}
.textContainer h3,h4 {
  margin-top: 28px;
}
.textContainer h5,h6 {
  margin-top: 22px;
}
</style>
