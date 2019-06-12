<template>
  <div>
    <el-backtop></el-backtop>
    <div class="header"
         :style="'background-image:url('+article.articleInfo.image+')'">
      <div class="headerInner" :style="'background-color:'+getImageColor()">
      </div>
    </div>
    <div class="headerImg">
      <el-image :src="article.articleInfo.image"></el-image>
    </div>
    <div class="headerTitle">
      <h1>{{article.articleInfo.title}}</h1>
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
    <v-footer></v-footer>
  </div>
</template>
<script>
import vFooter from '../components/Footer.vue';
export default {
  components:{
    vFooter,
  },
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
      color:'',
      commentList: [],
      dividerArray: [
        "头上一片晴天，心中一个想念",
        "想把房子卖了养你，但是房东不肯",
        "过了这个村，依旧有这个店，因为是连锁店",
        "一个人的作业做错了，结果全班人的都错了...",
        "胖的原因大概就是，瘦小的身躯，容不下我伟大的人格",
        "你看一晃两三年，匆匆又夏天",
        "得之我幸，失之我命，如此而已",
        "梦短梦长俱是梦，年来年去是何年",
        "但得夕阳无限好，何须惆怅近黄昏",
        "吹灭读书灯，一身都是月",
        "反正山高水长,我还有一生可以嚣张",
        "\"你笑时，雷声温柔，暴雨无声\"",
        "#define mian main",
        "Hello World",
        "#define true false",
      ],
      dividerContent: '头上一片晴天，心中一个想念',
    }
  },
  mounted () {
    this.$nextTick(function () {
      setInterval(this.timer, 10000);
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
            console.clear();
            that.article = res.data.data;
            that.color = that.getImageColor();
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
    },
    getImageColor () {
      let canvas = document.createElement('canvas'); 
      var img = new Image();
      img.src=this.article.articleInfo.image;
      canvas.width = img.width;
      canvas.height = img.height;
      var context = canvas.getContext("2d");
      context.drawImage(img, 0, 0);
      // 获取像素数据
      var data = context.getImageData(0, 0, img.width, img.height).data;
      let r = 0, g = 0, b = 0;
      // 取所有像素的平均值
      for (var row = 0; row < img.height; row++) {
        for (var col = 0; col < img.width; col++) {
          r += data[((img.width * row) + col) * 4];
          g += data[((img.width * row) + col) * 4 + 1];
          b += data[((img.width * row) + col) * 4 + 2];
        }
      }
      // 求取平均值
      r /= (img.width * img.height);
      g /= (img.width * img.height);
      b /= (img.width * img.height);
      // 将最终的值取整
      r = Math.round(r);
      g = Math.round(g);
      b = Math.round(b);
      return "rgb(" + r + "," + g + "," + b + ",0.97)";
    },
  },
  created () {
    this.getArticle();
    this.getComment();
  }
}
</script>
<style scoped>
.headerImg{
  height:220px;
  width:700px;
  background-size: cover;
  /* position:absolute; */
  border: #fff 5px solid;
  margin: -110px auto;
  z-index: -1;
}

.headerInner {
  top: 0;
  height: 560px;
}
.header {
  top: 0;
  height: 560px;

  background-image: url("../assets/img/background.jpg");
  background-position-x: 50%;
  background-size: auto 100%;
  text-align: cover;
  background-repeat: no-repeat;
}
.headerTitle {
  position: absolute;
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
  margin: 130px auto 50px;
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
