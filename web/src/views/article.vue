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
    <el-divider content-position="center">{{dividerContent}}</el-divider>
    <div style="width:70%;margin:0 auto;">
      <el-table :data="commentList"
                empty-text="没有评论噢...">
        <el-table-column>
          <template slot-scope="scope">
            来自<span class="site">{{scope.row.name}}</span>的评论:{{scope.row.content}}
          </template>
        </el-table-column>
        <el-table-column width="150">
          <template slot-scope="scope">
            <i>
              <el-link target="_blank"
                       :href="'http://'+scope.row.site">{{scope.row.site}}</el-link>
            </i>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div style="width:70%;margin:25px auto;border-radius: 4px;box-shadow: 0 2px 12px 0 rgba (0, 0, 0, 0.1)">
      <el-form :model="form"
               ref="ruleForm"
               label-width="100">
        <h4>评论</h4>
        <el-form-item label="昵称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email"></el-input>
        </el-form-item>
        <el-form-item label="网址">
          <el-input v-model="form.site">
            <template slot="prepend">http://</template>
          </el-input>
        </el-form-item>
        <el-form-item label="评论文本">
          <el-input v-model="form.content"
                    type="textarea"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"
                     @click="addComment">提交评论</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
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
      form: {
        articleId: '',
        name: '',
        email: '',
        site: '',
        content: '',
      },
      commentList: [],
      dividerArray: [
        "头上一片晴天，心中一个想念",
        "想把房子卖了养你，但是房东不肯",
        "过了这个村，依旧有这个店，因为是连锁店",
        "一个人的作业做错了，结果全班人的都错了...",
        "胖的原因大概就是，瘦小的身躯，容不下我伟大的人格",
      ],
      dividerContent: '头上一片晴天，心中一个想念',
    }
  },
  mounted () {
    this.$nextTick(function () {
      setInterval(this.timer, 7000);
    })
  },
  methods: {
    timer () {
      let n = Number.parseInt((Math.random()) * (this.dividerArray.length));
      let str = String(this.dividerArray[Number(n)]);
      this.dividerContent = str;
    },
    resetForm () {
      this.$refs['ruleForm'].resetFields();
    },
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
    },
    getComment () {
      let that = this;
      if (this.articleId != null) {
        this.$axios.get(this.URL_DEFINE_ROOT + 'user/articleComment/getArticleCommentByArticleId', { params: { articleId: this.articleId } }).then(function (res) {
          if (res.data.status == "success") {
            that.commentList = res.data.data;
          } else {
            that.$message.error(res.data.data.errMsg);
          }
        }).catch(function (err) {
          console.log(err);
        })
      }
    },
    addComment () {
      let that = this;
      this.form.articleId = this.articleId;
      if (this.form.name == '') {
        this.$message.error('昵称不能为空');
        return;
      }
      if (this.form.content == '') {
        this.$message.error('内容不能为空');
        return;
      }
      let reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
      if (this.form.email != '' && !reg.test(this.form.email)) {
        this.$message.error('邮箱格式错误');
        return;
      }
      this.$axios.post(this.URL_DEFINE_ROOT + 'user/articleComment/addArticleComment', this.form).then(function (res) {
        if (res.data.status == "success") {
          that.$message.success('添加成功，等待审核');
          that.form.content = '';
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })
    }
  },
  created () {
    this.getArticle();
    this.getComment();
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

  /* -webkit-filter: blur(10px); Chrome, Opera
       -moz-filter: blur(10px);
        -ms-filter: blur(10px);    
            filter: blur(10px); */
}
.headerTitle {
  position: relative;
  top: 150px;
  width: 100%;
  margin: 0 auto;
  text-align: center;
  font-size: 37px;
  color: #fff;
  letter-spacing: 8px;
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
.textContainer h1,
h2 {
  margin-top: 32px;
}
.textContainer h3,
h4 {
  margin-top: 28px;
}
.textContainer h5,
h6 {
  margin-top: 22px;
}
.site :hover {
  color: rgb(235, 72, 72);
}
.site :active {
  color: rgb(235, 72, 72);
}
.site {
  color: #909399;
}
</style>
