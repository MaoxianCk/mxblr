<template>
  <div>
    <el-form>
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
                      trigger="hover"
                      >
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
  </div>
</template>
<script>
export default {
  data () {
    return {
      URL_DEFINE_ROOT: '/local/admin',
      tagList: [],
      imageList: [],
      form: {
        title: '',
        content: '',
        tagId: null,
        imageId:null,
      }
    }
  },
  created () {
    this.getTagList();
    this.getImageList();
  },
  methods: {
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
}
</script>
<style scoped>
.ml10 {
  margin-left: 10px;
}
</style>
