<template>
  <div class="table">
    <div class="crumbs">
      <el-breadcrumb separator="">
        <el-breadcrumb-item>
          用户管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <el-table :data="userList"
                border
                stripe
                row-key="userId">
        <el-table-column type="index"
                         width="55"
                         label="序号">
        </el-table-column>
        <el-table-column prop="account"
                         width="200"
                         label="账号">
        </el-table-column>
        <el-table-column prop="name"
                         label="用户名">
        </el-table-column>
        <el-table-column prop="email"
                         width="200"
                         label="用户邮箱">
        </el-table-column>
        <el-table-column prop="phone_number"
                         width="200"
                         label="电话号码">
        </el-table-column>
        <el-table-column prop="count"
                         width="150"
                         label="登录次数">
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
        <el-table-column prop="role"
                         width="70"
                         :formatter="statusFormatter"
                         label="权限">
        </el-table-column>
        <el-table-column label="操作"
                         width="180">
          <template slot-scope="scope">
            <el-button type="warning"
                       @click="handleUpdate(scope.row)">移至回收站</el-button>
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
      userList: [],
    }
  },
  methods: {
    dateFormatter (row) {
      return row.modifiedTime.split(" ")[0];
    },
    statusFormatter (row) {
      switch (row.status) {
        case 0:
          return '1';
        case 1:
          return '2';
      }
    },
    getUserList () {
      let that = this;
      this.$axios.get(this.URL_DEFINE_ROOT + '/#').then(function (res) {
        if (res.data.status == "success") {
          that.UserList = res.data.data;
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })
    },
    handleUpdate () {
      let that = this;
      this.$axios.post(this.URL_DEFINE_ROOT + '/#').then(function (res) {
        if (res.data.status == "success") {
          that.UserList = res.data.data;
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })

    }
  },
  created () {
    this.getUserList();
  },
}
</script>
<style scoped>
</style>
