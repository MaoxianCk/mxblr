<template>
  <div class="header">
    <!-- 折叠按钮 -->
    <div
      class="collapse-btn"
      @click="collapseChage"
    >
      <i class="el-icon-menu"></i>
    </div>
    <div class="logo">幼教后台管理系统</div>
    <div class="header-right">
      <div class="header-user-con">
        <!-- 全屏显示 -->
        <div
          class="btn-fullscreen"
          @click="handleFullScreen"
        >
          <el-tooltip
            effect="dark"
            :content="fullscreen?`取消全屏`:`全屏`"
            placement="bottom"
          >
            <i class="el-icon-rank"></i>
          </el-tooltip>
        </div>
        <!-- 用户头像 -->
        <div class="user-avator"><img src="../assets/img/img.jpg"></div>
        <!-- 用户名下拉菜单 -->
        <el-dropdown
          class="user-name"
          trigger="click"
          @command="handleCommand"
        >
          <span class="el-dropdown-link">
            {{name}} <i class="el-icon-caret-bottom"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item
              divided
              command="modifyInfo"
            >个人信息修改</el-dropdown-item>
            <el-dropdown-item
              divided
              command="loginout"
            >退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
    <el-dialog
      title="个人信息修改"
      :visible.sync="editVisible"
      :before-close="handleClose"
      width="400px"
      center
    >
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item
          prop="oldPassword"
          label="旧密码："
        >
          <el-input
            type="password"
            v-model="form.oldPassword"
          ></el-input>
        </el-form-item>
        <el-form-item
          prop="password"
          label="新密码："
        >
          <el-input
            type="password"
            v-model="form.password"
          ></el-input>
        </el-form-item>
      </el-form>

      <span
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="modify"
        >确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import bus from '../assets/js/bus.js';
import QS from 'qs';
export default {
  data() {
    return {
      BASE_URL: '/local/',
      collapse: false,
      fullscreen: false,
      name: '',
      message: 2,
      editVisible: false,
      form: {
        oldPassword: "",
        password: ""
      },
      rules: {
        oldPassword: [
          { required: true, message: '请输入旧密码', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入新密码', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.name = sessionStorage.getItem("username");
  },
  methods: {
    handleClose(done) {
      this.form.userName = '';
      this.form.password = '';
      let that = this;
      this.$confirm("确认关闭？")
        .then(function () {
          done();
          that.$refs['form'].resetFields();
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    // 用户名下拉菜单选择事件
    handleCommand(command) {
      if (command == 'loginout') {
        sessionStorage.removeItem('username')
        sessionStorage.removeItem('role')
        sessionStorage.removeItem('schoolId')
        this.$router.push('/login');
      }
      if (command == 'modifyInfo') {
        this.editVisible = true;
      }
    },
    modify() {
      if (this.form.oldPassword == "") {
        this.$message.error("请输入旧密码");
      } else if (this.form.password == "") {
        this.$message.error("请输入新密码");
      } else {
        this.$axios.post(this.BASE_URL + '/admin/adminManage/modifyInfo', QS.stringify({
          oldPassword: this.form.oldPassword,
          password: this.form.password
        })).then((res) => {
          if (res.data.status == 'success') {
            this.$message({
              message: '修改成功',
              type: 'success'
            });
            this.form.oldPassword = '';
            this.form.password = '';
            this.editVisible = false;
          } else {
            this.$message.error("旧密码有误");
          }
        }).catch((err) => {
          //这里为请求失败时的处理
          this.$message.error("未知错误");
          this.editVisible = false;
          console.log(err)
        })
      }
    },
    // 侧边栏折叠
    collapseChage() {
      this.collapse = !this.collapse;
      bus.$emit('collapse', this.collapse);
    },
    // 全屏事件
    handleFullScreen() {
      let element = document.documentElement;
      if (this.fullscreen) {
        if (document.exitFullscreen) {
          document.exitFullscreen();
        } else if (document.webkitCancelFullScreen) {
          document.webkitCancelFullScreen();
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen();
        } else if (document.msExitFullscreen) {
          document.msExitFullscreen();
        }
      } else {
        if (element.requestFullscreen) {
          element.requestFullscreen();
        } else if (element.webkitRequestFullScreen) {
          element.webkitRequestFullScreen();
        } else if (element.mozRequestFullScreen) {
          element.mozRequestFullScreen();
        } else if (element.msRequestFullscreen) {
          // IE11
          element.msRequestFullscreen();
        }
      }
      this.fullscreen = !this.fullscreen;
    }
  },
  mounted() {
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
