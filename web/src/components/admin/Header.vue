<template>
  <div class="header">
    <!-- 折叠按钮 -->
    <div class="collapse-btn"
         @click="collapseChage">
      <i class="el-icon-menu"></i>
    </div>
    <div class="logo">毛线球上的喵星人</div>
    <div class="header-right">
      <div class="header-user-con">
        <!-- 用户头像 -->
        <div class="user-avator">
          <img src="../../assets/img/img.jpg">
        </div>
        <!-- 用户名下拉菜单 -->
        <el-dropdown class="user-name"
                     trigger="click"
                     @command="handleCommand">
          <span class="el-dropdown-link">
            {{name}} <i class="el-icon-caret-bottom"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item divided
                              command="modifyInfo">个人信息修改</el-dropdown-item>
            <el-dropdown-item divided
                              command="modifyPassword">密码修改</el-dropdown-item>
            <el-dropdown-item divided
                              command="loginout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

        <!--个人信息修改对话框-->
        <el-dialog title="个人信息修改"
                   :visible.sync="editUserInfoSwitch"
                   :before-close="handleClose"
                   width="400px"
                   center>
          <el-form ref="userInfoForm"
                   :model="userInfoForm"
                   :rules="rules"
                   label-width="100px">
            <el-form-item prop="name"
                          label="昵称：">
              <el-input v-model="userInfoForm.name"></el-input>
            </el-form-item>
            <el-form-item prop="phoneNumber"
                          label="手机号：">
              <el-input v-model="userInfoForm.phoneNumber"></el-input>
            </el-form-item>
            <el-form-item prop="email"
                          label="邮箱：">
              <el-input v-model="userInfoForm.email"></el-input>
            </el-form-item>
          </el-form>

          <span slot="footer"
                class="dialog-footer">
            <el-button @click="editUserInfoSwitch = false">取 消</el-button>
            <el-button type="primary"
                       @click="modifyUserInfo">确 定</el-button>
          </span>
        </el-dialog>

        <!--个人信息修改对话框-->
        <el-dialog title="密码修改"
                   :visible.sync="editUserPasswordSwitch"
                   :before-close="handleClose"
                   width="400px"
                   center>
          <el-form ref="userPasswordForm"
                   :model="userPasswordForm"
                   :rules="rules"
                   label-width="100px">
            <el-form-item prop="password"
                          label="新密码：">
              <el-input type="password"
                        v-model="userPasswordForm.password"></el-input>
            </el-form-item>
          </el-form>

          <span slot="footer"
                class="dialog-footer">
            <el-button @click="editUserPasswordSwitch = false">取 消</el-button>
            <el-button type="primary"
                       @click="modifyUserPassword">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </div>
  </div>
</template>
<script>
import QS from 'qs';
import bus from '../../assets/js/bus.js';
export default {
  data () {
    return {
      URL_DEFINE_ROOT: '/local/',
      collapse: false,
      name: '',
      editUserInfoSwitch: false,
      editUserPasswordSwitch: false,
      userInfoForm: {
        userId: "",
        email: "",
        name: "",
        phoneNumber: "",
        userInfoId: ""
      },
      userPasswordForm: {
        password: ""
      }
    }
  },
  created () {
    this.name = sessionStorage.getItem("name");
  },
  methods: {
    handleClose (done) {
      let that = this;
      this.$confirm("确认关闭？")
        .then(function () {
          done();
          that.$refs['userInfoForm'].resetFields();
          that.$refs['userPasswordForm'].resetFields();
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    // 用户名下拉菜单选择事件
    handleCommand (command) {
      if (command == 'loginout') {
        sessionStorage.removeItem('userInfoId')
        sessionStorage.removeItem('userId')
        sessionStorage.removeItem('name')
        sessionStorage.removeItem('role')
        this.$router.push('/adminlogin');
      }
      if (command == 'modifyInfo') {
        this.editUserInfoSwitch = true;
      }
      if (command == 'modifyPassword') {
        this.editUserPasswordSwitch = true;
      }
    },
    modifyUserInfo () {
      let that = this;
      let data = {
        "email": this.userInfoForm.email,
        "name": this.userInfoForm.name,
        "phoneNumber": this.userInfoForm.phoneNumber,
        "userId": sessionStorage.getItem("userId"),
        "userInfoId": sessionStorage.getItem("userInfoId")
      }
      this.$axios.post(this.URL_DEFINE_ROOT + '/maoxianUser/modifyUserInfo', data).then(function (res) {
        if (res.data.status == "success") {
          that.$message.success("success");
          sessionStorage.setItem("name", that.userInfoForm.name);
          that.name = that.userInfoForm.name;
          that.clearForm();
          that.editUserInfoSwitch = false;
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })
    },
    modifyUserPassword () {
      let that = this;
      this.$axios.post(this.URL_DEFINE_ROOT + '/maoxianUser/modifyPassword', QS.stringify({
        password: this.userPasswordForm.password,
        userId: sessionStorage.getItem("userId")
      })).then(function (res) {
        if (res.data.status == "success") {
          that.$message.success("success");
          that.clearForm();
          that.editUserPasswordSwitch = false;
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })
    },
    //表单清空
    clearForm () {
      //用户信息
      this.userInfoForm.userId = "";
      this.userInfoForm.userInfoId = "";
      this.userInfoForm.email = "";
      this.userInfoForm.name = "";
      this.userInfoForm.phoneNumber = "";
      //用户密码
      this.userPasswordForm.userId = "";
      this.userPasswordForm.password = "";
    },
    // 侧边栏折叠
    collapseChage () {
      this.collapse = !this.collapse;
      bus.$emit('collapse', this.collapse);
    },
  },
  mounted () {
    if (document.body.clientWidth < 1500) {
      this.collapseChage();
    }
  }
}
</script>
<style scoped>
.header {
  position: relative;
  box-sizing: border-box;
  width: 100%;
  height: 70px;
  font-size: 22px;
  color: #fff;
}
.collapse-btn {
  float: left;
  padding: 0 21px;
  cursor: pointer;
  line-height: 70px;
}
.header .logo {
  float: left;
  width: 250px;
  line-height: 70px;
}
.header-right {
  float: right;
  padding-right: 50px;
}
.header-user-con {
  display: flex;
  height: 70px;
  align-items: center;
}
.btn-fullscreen {
  transform: rotate(45deg);
  margin-right: 5px;
  font-size: 24px;
}
.btn-bell,
.btn-fullscreen {
  position: relative;
  width: 30px;
  height: 30px;
  text-align: center;
  border-radius: 15px;
  cursor: pointer;
}
.btn-bell-badge {
  position: absolute;
  right: 0;
  top: -2px;
  width: 8px;
  height: 8px;
  border-radius: 4px;
  background: #f56c6c;
  color: #fff;
}
.btn-bell .el-icon-bell {
  color: #fff;
}
.user-name {
  margin-left: 10px;
}
.user-avator {
  margin-left: 20px;
}
.user-avator img {
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
.el-dropdown-link {
  color: #fff;
  cursor: pointer;
}
.el-dropdown-menu__item {
  text-align: center;
}
</style>
