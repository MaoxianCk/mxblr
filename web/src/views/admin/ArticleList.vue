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
      <!-- 班级主页面表格 -->
      <el-table :data="articleList"
                border
                stripe
                row-key="articleId">
        <el-table-column type="index"
                         width="55"
                         label="序号">
        </el-table-column>
        <el-table-column prop="title"
                         label="标题">
        </el-table-column>
        <el-table-column prop="userName"
                         width="100"
                         label="作者">
        </el-table-column>
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
            <el-button type="primary">编辑</el-button>
            <el-button type="danger"
                       @click="handleDelete(scope.row)">移至回收站</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      URL_DEFINE_ROOT: '/local/admin',
      articleList: [],
    }
  },
  methods: {
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
    }
  },
  created () {
    this.getArticleList();
  },
}
</script>

<style scoped>
</style>
