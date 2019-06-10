<template>
  <el-form class="login"
           ref="form"
           :model="form"
           :rules="rules">
    <div class="loginContent">
      <el-form-item label="账号"
                    prop="account"
                    :error="errMsg">
        <el-input v-model="form.account"></el-input>
      </el-form-item>
      <el-form-item label="密码"
                    prop="password"
                    :error="errMsg">
        <el-input type="password"
                  v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary"
                   style="width:240px;"
                   @click="submitForm()">登录</el-button>
      </el-form-item>
    </div>
  </el-form>
</template>

<script>
export default {
  data: function () {
    return {
      URL_DEFINE_ROOT: '/local/',
      errMsg: null,
      form: {
        account: '',
        password: ''
      },
      rules: {
        account: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ]
      }
    }
  },
  methods: {
    submitForm () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.login();
        }
        else {
          return false;
        }
      });
    },
    login () {
      var params = new URLSearchParams();
      params.append('account', this.form.account);
      params.append('password', this.form.password);
      let that = this;
      this.$axios.post(this.URL_DEFINE_ROOT + '/maoxianUser/login', params).then(function (res) {
        if (res.data.status == "success") {
          that.$message.success('登录成功');
          sessionStorage.setItem("userInfoId", res.data.data.userInfoId);
          sessionStorage.setItem("userId", res.data.data.userId);
          sessionStorage.setItem("role", res.data.data.role);
          sessionStorage.setItem("name", res.data.data.name);
          that.$router.push('/admininstrument');
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })
    }
  },
  created () {

  }
}
</script>

<style>
.login {
  position: absolute;
  left: 50%;
  top: 50%;
  margin: -150px;
  width: 300px;
  border-radius: 5px;
  background-color: rgb(212, 212, 212);
  overflow: hidden;
}
.loginContent {
  padding: 15px 30px 5px;
}
</style>