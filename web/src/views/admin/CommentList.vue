<template>
  <div class="table">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          评论管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <el-table :data="commentList"
                border
                stripe
                row-key="articleId">
        <el-table-column type="index"
                         width="55"
                         label="序号">
        </el-table-column>
        <el-table-column label="文章标题"
                         prop="title">
        </el-table-column>
        <el-table-column prop="name"
                         label="评论者">
        </el-table-column>
        <el-table-column prop="content"
                         width="200"
                         label="评论内容">
        </el-table-column>
        <el-table-column prop="email"
                         label="邮箱地址">
        </el-table-column>
        <el-table-column prop="site"
                         label="个人网址">
        </el-table-column>
        <el-table-column prop="status"
                         :formatter="formatStatus"
                         width="60"
                         label="审核">
        </el-table-column>
        <el-table-column prop="ip"
                         width="100"
                         label="IP">
        </el-table-column>
        <el-table-column prop="createdTime"
                         :formatter="formatDate"
                         sortable
                         width="100"
                         label="创建日期">
          <template slot-scope="scope">
            <el-tooltip class="item"
                        effect="light"
                        :open-delay="500"
                        :hide-after="1600"
                        :enterable='false'
                        :content="scope.row.createdTime"
                        placement="bottom-start">
              <label>{{formatDate(scope.row)}}</label>
            </el-tooltip>
          </template>
        </el-table-column>
        <el-table-column label="操作"
                         width="200">
          <template slot-scope="scope">
            <el-button v-if="handleStatusBtn(scope.row)"
                       type="warning"
                       disabled>批准</el-button>
            <el-button v-else
                       type="warning"
                       @click="handleStatus(scope.row,StatusApprove)">批准</el-button>
            <el-button type="danger"
                       @click="handleStatus(scope.row,StatusDelete)">移至回收站</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import QS from 'qs';
export default {
  data () {
    return {
      URL_DEFINE_ROOT: '/local/admin',
      commentList: [],
      //评论状态为已批准
      StatusApprove: 1,
      //评论状态为逻辑删除
      StatusDelete: 2,
    }
  },
  methods: {
    //处理状态按钮能否使用
    handleStatusBtn (row) {
      return row.status;
    },
    handleStatus (row, status) {
      let that = this;
      this.$axios.post(this.URL_DEFINE_ROOT + '/comment/modifyCommentStatus', QS.stringify({
        commentId: row.commentId,
        status: status
      })).then(function (res) {
        if (res.data.status == "success") {
          that.$message.success("success");
          that.getCommentList();
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })
    },
    getCommentList () {
      let that = this;
      this.$axios.get(this.URL_DEFINE_ROOT + '/comment/getArticleCommentWithArticle').then(function (res) {
        if (res.data.status == "success") {
          that.commentList = res.data.data;
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })
    },
    formatDate (row) {
      return row.createdTime.split(" ")[0];
    },
    formatStatus (row) {
      return row.status == 1 ? "批准" : "未批准";
    }
  },
  created () {
    this.getCommentList();
  },
}
</script>

<style scoped>
</style>
