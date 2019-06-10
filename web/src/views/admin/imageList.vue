<template>
  <div class="table">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          图片管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <el-button size="small"
                 type="primary"
                 @click="addImg">添加图片</el-button>
      <el-button size="small"
                 type="primary"
                 @click="getImageList">刷新</el-button>
      <el-table :data="imageList"
                class="mt10"
                border
                stripe>
        <el-table-column type="index"
                         width="50"
                         label="序号">
        </el-table-column>
        <el-table-column label="图片预览">
          <template slot-scope="scope">
            <el-popover placement="right"
                        title=""
                        trigger="hover">
              <el-image style="width: 350px; height: 165px"
                        :src="scope.row.image"
                        fit="contain"
                        slot="reference">
              </el-image>
              <el-image style="max-width:770px;"
                        :src="scope.row.image"
                        fit="fill">
              </el-image>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column prop="name"
                         width="200"
                         label="图片名称">
        </el-table-column>
        <el-table-column label="操作"
                         width="180">
          <template slot-scope="scope">
            <el-button type="danger"
                       @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog :visible.sync="addSwitch"
               :before-close="closeDialog">
      <el-upload action=''
                 :on-change="getFile"
                 ref='editupload'
                 accept="image/*"
                 :limit="1"
                 list-type="picture"
                 :on-remove="removePic"
                 :auto-upload="false">
        <el-button size="small"
                   type="primary">选择图片上传,最多上传一张图片</el-button>
      </el-upload>
      <el-input v-if="form.image!==''"
                class="mt10"
                label="图片名称"
                v-model="form.name"></el-input>
      <el-button type="priamry"
                 @click="confirmAdd"
                 class="mt10">
        确定
      </el-button>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data () {
    return {
      URL_DEFINE_ROOT: '/local/admin',
      imageList: [],
      addSwitch: false,
      form: {
        "name": '',
        "image": '',
      }
    }
  },
  methods: {
    closeDialog (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          _;
          this.form.name = '';
          this.form.image = '';
          done();
        })
        .catch(function () { });
    },
    handleDelete (row) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let data = [];
        let that = this;
        data.push(row.imageId);
        this.$axios.post(this.URL_DEFINE_ROOT + '/image/deleteImage', data).then(function (res) {
          if (res.data.status == 'success') {
            that.$message.success('删除成功');
            that.getImageList();
          }
          else {
            that.$message.error(res.data.data.errMsg);
          }
        }).catch(function (err) {
          console.log(err);
        })
      }).catch(() => { });
    },
    confirmAdd () {
      if (this.form.image == '') {
        this.$message.error('请上传一张图片');
        return;
      }
      let data = {
        'name': this.form.name,
        'image': this.form.image
      };
      let that = this;
      this.$axios.post(this.URL_DEFINE_ROOT + '/image/addImage', data).then(function (res) {
        if (res.data.status == 'success') {
          that.$message.success('添加成功');
          that.removePic();
          that.getImageList();
          that.addSwitch = false;
        }
        else {
          that.$message.error(res.data.data.errMsg);
        }
      }).catch(function (err) {
        console.log(err);
      })
    },
    addImg () {
      this.addSwitch = true;
      this.form.name = '';
      this.form.image = '';
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

    },
    //获取图片base64
    getFile (file) {
      this.form.name = file.name.replace(/(.*\/)*([^.]+).*/ig, "$2");
      this.getBase64(file.raw).then(res => {
        this.form.image = res
      });
    },
    getBase64 (file) {
      return new Promise(function (resolve, reject) {
        let reader = new FileReader();
        let imgResult = "";
        reader.readAsDataURL(file);
        reader.onload = function () {
          imgResult = reader.result;
        };
        reader.onerror = function (error) {
          reject(error);
        };
        reader.onloadend = function () {
          resolve(imgResult);
        };
      });
    },
    removePic () {
      console.log("删除我")
      this.form.image = '';
      this.form.name = '';
    },
  },
  created () {
    this.getImageList();
  }
}
</script>
<style scoped>
.mt10 {
  margin-top: 10px;
}
</style>
