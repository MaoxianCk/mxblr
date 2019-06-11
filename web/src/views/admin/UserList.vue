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
      <el-button class="md10"
                 type="primary"
                 @click="handleAdd">添加用户</el-button>
      <el-table :data="userList"
                border
                stripe
                row-key="userId">
        <el-table-column type="index"
                         width="55"
                         label="序号">
        </el-table-column>
        <el-table-column prop="account"
                         label="账号">
        </el-table-column>
        <el-table-column prop="email"
                         width="200"
                         label="用户邮箱">
        </el-table-column>
        <el-table-column prop="phoneNumber"
                         width="200"
                         label="电话号码">
        </el-table-column>
        <el-table-column prop="createdTime"
                         :formatter="dateFormatter"
                         sortable
                         width="150"
                         label="创建日期">
          <template slot-scope="scope">
            <el-tooltip class="item"
                        effect="light"
                        :open-delay="500"
                        :hide-after="1600"
                        :enterable='false'
                        :content="scope.row.createdTime"
                        placement="bottom-start">
              <label>{{dateFormatter(scope.row)}}</label>
            </el-tooltip>
          </template>
        </el-table-column>
        <el-table-column prop="modifiedTime"
                         :formatter="dateFormatter"
                         sortable
                         width="150"
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
                       @click="sendScope(scope.row)"
                       v-if="scope.row.role===0"
                       disabled="">修改权限</el-button>
            <el-button type="warning"
                       @click="sendScope(scope.row)"
                       v-else>修改权限</el-button>
            <el-button type="danger"
                       @click="open(scope.row)"
                       v-if="scope.row.role===0"
                       disabled="">删除</el-button>
            <el-button type="danger"
                       @click="open(scope.row)"
                       v-else>删除</el-button>
            <!-- @click="handleDelete(scope.row,scope.row.role) -->
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog title="权限修改"
               :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="修改权限"
                      :label-width="formLabelWidth">
          <el-select v-model="form.region"
                     placeholder="请选择权限">
            <el-option label="编辑"
                       value="1"></el-option>
            <el-option label="写手"
                       value="2"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary"
                   @click="handleModified(rows,form.region)">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="添加用户"
               :visible.sync="addSwitch">
      <el-form :model="form"
               label-width="60px">
        <el-form-item label="账号"
                      prop="name">
          <el-input v-model.trim="form.account"></el-input>
        </el-form-item>
        <el-form-item label="密码"
                      prop="password">
          <el-input type="password"
                    v-model="form.pass"></el-input>
        </el-form-item>
      </el-form>
      <!-- 提交按钮 -->
      <div slot="footer"
           class="dialog-footer">
        <el-button type="primary"
                   @click="confirmAdd">确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import QS from 'qs';
export default {
  data () {
    return {
      URL_DEFINE_ROOT: '/local/admin',
      userList: [],
      rows: null,
      role: 0,
      dialogFormVisible: false,
      addSwitch: false,
      form: {
        account: '',
        pass: '',
        region: '',
      },
      formLabelWidth: '120px',
    }
  },
  methods: {
    handleAdd () {
      this.form.account = '';
      this.addSwitch = true;
    },
    sendScope (row) {
      this.dialogFormVisible = true;
      this.rows = row;
    },
    dateFormatter (row) {
      return row.modifiedTime.split(" ")[0];
    },
    statusFormatter (row) {
      switch (row.role) {
        case 0:
          return '超管';
        case 1:
          return '编辑';
        case 2:
          return '写手'
      }
    },
    getUserList () {
      let that = this;
      this.$axios.get(this.URL_DEFINE_ROOT + '/user/getUserList').then(function (res) {
        if (res.data.status == "success") {
          that.userList = res.data.data;
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })
    },
    confirmAdd () {
      if (this.form.name == '') {
        this.$message.error("名称不能为空");
        return;
      }
      else if (this.form.pass == '') {
        this.$message.error("密码不能为空");
      }
      let that = this;
      this.$axios.post(this.URL_DEFINE_ROOT + '/user/addUser', QS.stringify({
        account: this.form.account,
        password: this.form.pass
      })).then(function (res) {
        if (res.data.status == "success") {
          that.$message.success("添加成功");
          that.getUserList();
          that.addSwitch = false;
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        that.$message.error("系统错误");
        console.log(err);
      })
    },
    handleDelete (row, role) {
      let that = this;
      console.log(role);
      this.$axios.post(this.URL_DEFINE_ROOT + '/user/deleteUser', QS.stringify({
        userId: row.userId,
        role: role
      })).then(function (res) {
        if (res.data.status == "success") {
          that.$message.success("删除成功");
          that.getUserList();
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })
    },
    handleModified (row, role) {
      this.dialogFormVisible = false;
      let that = this;
      console.log(role);
      this.$axios.post(this.URL_DEFINE_ROOT + '/user/modifyRole', QS.stringify({
        userId: row.userId,
        role: role
      })).then(function (res) {
        if (res.data.status == "success") {
          that.$message.success("修改成功");
          that.getUserList();
        }
        else {
          that.$message.error(res.data.date.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })
    },
    open (row) {
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.handleDelete(row, row.role);
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  },
  created () {
    this.getUserList();
  },
}
</script>

<style scoped>
.mt10 {
  margin-top: 10px;
}
.md10 {
  margin-bottom: 10px;
}
</style>
