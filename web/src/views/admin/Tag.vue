<template>
  <div class="table">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          分类管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <el-button class="md10"
                 type="primary"
                 @click="handleAdd">添加分类</el-button>
      <el-table :data="tagList">
        <el-table-column type="index"
                         width="50"
                         label="序号">
        </el-table-column>
        <el-table-column prop="name"
                         label="分类名称">
        </el-table-column>
        <el-table-column width="150"
                         label="操作">
          <template slot-scope="scope">
            <el-button type="primary"
                       @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger"
                       @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog title="添加分类"
               :visible.sync="addSwitch">
      <el-form :model="form"
               label-width="60px">
        <el-form-item label="名称"
                      prop="name">
          <el-input v-model.trim="form.name"></el-input>
        </el-form-item>
      </el-form>
      <!-- 提交按钮 -->
      <div slot="footer"
           class="dialog-footer">
        <el-button type="primary"
                   @click="confirmAdd">确认</el-button>
      </div>
    </el-dialog>
    <el-dialog title="修改分类"
               :visible.sync="editSwitch">
      <el-form :model="form"
               label-width="60px">
        <el-form-item label="名称"
                      prop="name">
          <el-input v-model.trim="form.name"></el-input>
        </el-form-item>
      </el-form>
      <!-- 提交按钮 -->
      <div slot="footer"
           class="dialog-footer">
        <el-button type="primary"
                   @click="confirmEdit">确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data () {
    return {
      URL_DEFINE_ROOT: '/local/admin',
      tagList: [],

      addSwitch: false,
      editSwitch: false,
      form: {
        tagId: '',
        name: '',
      },
    }
  },
  methods: {
    handleAdd () {
      this.form.name = '';
      this.addSwitch = true;
    },
    handleEdit (row) {
      this.editSwitch = true;
      this.form.name = row.name;
      this.form.tagId = row.tagId;
    },
    handleDelete (row) {
      let that = this;
      let data = [];
      data.push(row.tagId);
      this.$confirm('确认删除？')
        .then(function () {
          that.$axios.post(that.URL_DEFINE_ROOT + '/tag/deleteTag', data).then(function (res) {
            if (res.data.status == "success") {
              that.$message.success("删除成功");
              that.getTagList();
            } else {
              that.$message.error(res.data.data.errMsg);
            }
          }).catch(function (err) {
            that.$message.error("系统错误");
            console.log(err);
          })
        })
        .catch();
    },
    confirmAdd () {
      if (this.form.name == '') {
        this.$message.error("名称不能为空");
        return;
      }
      let that = this;
      let params = new URLSearchParams();
      params.append('name', this.form.name);
      this.$axios.post(this.URL_DEFINE_ROOT + '/tag/addTag', params).then(function (res) {
        if (res.data.status == "success") {
          that.$message.success("添加成功");
          that.getTagList();
          that.addSwitch = false;
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        that.$message.error("系统错误");
        console.log(err);
      })
    },
    confirmEdit () {
      if (this.form.name == '') {
        this.$message.error("名称不能为空");
        return;
      }
      let that = this;
      let params = new URLSearchParams();
      params.append('name', this.form.name);
      params.append('tagId', this.form.tagId);
      this.$axios.post(this.URL_DEFINE_ROOT + '/tag/modifyTag', params).then(function (res) {
        if (res.data.status == "success") {
          that.$message.success("修改成功");
          that.getTagList();
          that.editSwitch = false;
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        that.$message.error("系统错误");
        console.log(err);
      })
    },
    getTagList () {
      let that = this;
      this.$axios.get(this.URL_DEFINE_ROOT + '/tag/getTagList').then(function (res) {
        if (res.data.status == "success") {
          that.tagList = res.data.data;
        } else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        that.$message.error("系统错误");
        console.log(err);
      })
    },
  },
  created () {
    this.getTagList();
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
