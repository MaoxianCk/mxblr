<template>
  <div>
    <div class="header">
      <div class="headerTitle">
        <h1>毛线球上的喵星人</h1>
      </div>
    </div>
    <div class="textContainer">
      <div class="articleInfo"
           v-for="(item,index) in articleInfo"
           :key="index">
        <img :src="item.image">
        <div class="articleTitle"
             @click="jumpTo(item.articleId)">{{item.title}}</div>
        <div class="articleDetail">{{item.createdTime}}</div>
        <div class="articleSummary">{{item.summary}}</div>
        <div class="spitLine"></div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data: function () {
    return {
      URL_DEFINE_ROOT: '/local/',
      articleInfo: [],
    }
  },
  methods: {
    jumpTo (id) {
      this.$router.push({ path: '/article', query: { "id": id } });
    },
    getArticleInfos () {
      let that = this;
      this.$axios.get(this.URL_DEFINE_ROOT + '/user/article/getArticleInfoList').then(function (res) {
        if (res.data.status == "success") {
          that.articleInfo = res.data.data;
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })
    }
  },
  created () {
    this.getArticleInfos();
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
  font-size: 30px;
  color: #fff;
}
.textContainer {
  background-color: #fff;
  width: 700px;
  min-height: 300px;
  margin: 50px auto;
}
.articleInfo {
  height: 373px;
  width: 700px;
  margin: 50px auto 5px;
  background-color: #fff;
}

.articleTitle {
  margin-top: 15px;
  transition: color 0.2s;
  color: #000000;
  font-size: 32px;
  text-decoration-line: none;
}

.articleTitle:visited {
  margin-top: 15px;
  transition: color 0.2s;
  color: #000000;
  font-size: 32px;
  text-decoration-line: none;
}

.articleTitle:focus {
  margin-top: 15px;
  transition: color 0.2s;
  color: #000000;
  font-size: 32px;
  text-decoration-line: none;
}

.articleTitle:active {
  margin-top: 15px;
  transition: color 0.2s;
  color: #000000;
  font-size: 32px;
  text-decoration-line: none;
}

.articleTitle:hover {
  cursor: pointer;
  color: #2aabd2;
  text-decoration-line: none;
}

.articleDetail {
  margin-top: 10px;
  font-size: 16px;
  color: #c5c5c5;
}

.articleSummary {
  margin-top: 10px;
  font-size: 16px;
  color: #b3b3b3;
}

.spitLine {
  margin-top: 50px;
  margin-left: auto;
  margin-right: auto;
  height: 2px;
  width: 70px;
  background-color: #c7c7c7;
}
</style>
