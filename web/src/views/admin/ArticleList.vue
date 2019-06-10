<template>
  <div class="table">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          文章管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <el-table :data="articleList"
                border
                stripe
                row-key="articleId">
        <el-table-column type="index"
                         width="55"
                         label="序号">
        </el-table-column>
        <el-table-column label="标题">
          <template slot-scope="scope">
            <el-link target="_blank"
                     :href="'/article?id='+scope.row.articleId">{{scope.row.title}}</el-link>
          </template>
        </el-table-column>
        <!-- <el-table-column prop="userName"
                         width="100"
                         label="作者">
        </el-table-column> -->
        <el-table-column prop="tagName"
                         width="100"
                         label="分类目录">
        </el-table-column>
        <el-table-column prop="modifiedTime"
                         :formatter="dateFormatter"
                         sortable
                         width="100"
                         label="修改日期">
          <template slot-scope="scope">
            <el-tooltip class="item"
                        effect="light"
                        :open-delay="500"
                        :hide-after="1600"
                        :enterable='false'
                        :content="scope.row.modifiedTime"
                        placement="bottom-start">
              <label>{{dateFormatter(scope.row)}}</label>
            </el-tooltip>
          </template>
        </el-table-column>
        <el-table-column prop="status"
                         width="70"
                         :formatter="statusFormatter"
                         label="状态">
        </el-table-column>
        <el-table-column label="操作"
                         width="180">
          <template slot-scope="scope">
            <el-button type="primary"
                       @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger"
                       @click="handleDelete(scope.row)">移至回收站</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog :visible.sync="editSwitch"
               fullscreen>
      <el-form>
        <h4>修改文章</h4>
        <el-form-item prop="title">
          <el-input v-model="form.title"
                    placeholder="题目"></el-input>
        </el-form-item>
        <el-form-item>
          <el-select v-model="form.tagId">
            <el-option v-for="(item,index) in tagList"
                       :key="index"
                       :label="item.name"
                       :value="item.tagId">
            </el-option>
          </el-select>
          <el-select v-model="form.imageId"
                     class="ml10">
            <el-popover v-for="(item,index) in imageList"
                        :key="index"
                        placement="right"
                        title=""
                        trigger="hover">
              <el-option :label="item.name"
                         :value="item.imageId"
                         slot="reference">
              </el-option>
              <el-image style="width: 350px; height: 165px"
                        :src="item.image"
                        fit="contain">
              </el-image>
            </el-popover>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-input type="textarea"
                    v-model="form.content"
                    :autosize="{ minRows: 30,maxRows: 30}"
                    placeholder="文本内容"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="addArticle"
                     type="primary">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import QS from 'qs';
export default {
  data () {
    return {
      URL_DEFINE_ROOT: '/local/admin',
      articleList: [],
      tagList: [],
      imageList: [],
      form: {
        title: '',
        content: '',
        tagId: null,
        imageId: null,
      },
      editSwitch: false,
    }
  },
  methods: {
    handleEdit (row) {
      this.getArticle(row.articleId);
      this.getTagList();
      this.getImageList();
      this.editSwitch = true;
    },
    handleDelete (row) {
      let that = this;
      this.$axios.post(this.URL_DEFINE_ROOT + '/article/deleteArticle', QS.stringify({
        articleId: row.articleId
      })).then(function (res) {
        if (res.data.status == "success") {
          that.$message.success("删除成功");
          that.getArticleList();
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })
    },
    dateFormatter (row) {
      return row.modifiedTime.split(" ")[0];
    },
    statusFormatter (row) {
      switch (row.status) {
        case 0:
          return '未发布';
        case 1:
          return '已发布';
      }
    },
    getArticleList () {
      let that = this;
      this.$axios.get(this.URL_DEFINE_ROOT + '/article/getAdminArticleInfoList').then(function (res) {
        if (res.data.status == "success") {
          that.articleList = res.data.data;
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })
    },
    getArticle (articleId) {
      let that = this;
      if (articleId != null) {
        this.$axios.get('/local/user/article/getArticleById', { params: { articleId: articleId } }).then(function (res) {
          if (res.data.status == "success") {
            let article = res.data.data;
            that.form.title = article.articleInfo.title;
            that.form.content = article.content;
            that.form.tagId = article.articleInfo.tagId;
            that.form.imageId = article.articleInfo.imageId;
          } else {
            that.$message.error(res.data.data.errMsg);
          }
        }).catch(function (err) {
          console.log(err);
        })
      }
    },
    getTagList () {
      let that = this;
      this.$axios.get('/local/user/tag/getTagList').then(function (res) {
        if (res.data.status == "success") {
          that.tagList = res.data.data;
          if (that.tagList.length <= 0) {
            that.$message.error('没有分类信息，请先添加分类');
            that.$router.push('/admintag');
            return;
          }
          if (that.form.tagId === null) {
            that.form.tagId = that.tagList[0].tagId;
          }
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })
    },
    addArticle () {
      let that = this;
      this.$axios.post(this.URL_DEFINE_ROOT + '/article/addArticle', this.form).then(function (res) {
        if (res.data.status == "success") {
          that.$message.success("success");
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })
    },
    getImageList () {
      let that = this;
      this.$axios.get(this.URL_DEFINE_ROOT + '/image/getImageList').then(function (res) {
        if (res.data.status == "success") {
          that.imageList = res.data.data;
          console.log(JSON.stringify(that.imageList));
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })
    }
  },
  created () {
    this.getArticleList();
  },
}
</script>

<style scoped>
</style>
