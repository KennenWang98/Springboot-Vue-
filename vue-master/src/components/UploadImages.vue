<template>
  <div>
    <van-divider contentPosition="left">最多选择五张照片</van-divider>
    <div>
      <van-uploader :file-list="fileList" accept="image" @afterRead="afterRead" max-count="5" @delete="deleteImage"/>
    </div>
    <div>
      <div class="centerButton">
        <van-button color="linear-gradient(to right, #4bb0ff, #6149f6)" @click="UploadImageDoing" size="normal">确认</van-button>
      </div>
    </div>
    <i-message id="message"></i-message>
  </div>
</template>
<script>
import {mapState} from 'vuex'
import StorageUtil from '../utils/StorageUtil'
export default {
  data () {
    return {
      fileList: []
    }
  },
  methods: {
    afterRead (event) {
      const filePath = event.mp.detail.file.path
      const fileName = event.mp.detail.file.name
      this.fileList.push({name: fileName, url: filePath, isImage: true})
    },
    UploadImageDoing () {
      const fileList = this.fileList
      const classId = this.classroom.classId
      const user = StorageUtil.getUser()
      const phoneNumber = user.phoneNumber
      this.$store.dispatch('setNewMicrospherePictures', {fileList, classId, phoneNumber})
      this.$emit('UploadImageDoing')
    },
    deleteImage (event) {
      this.fileList.splice(event.mp.detail.index, 1)
    }
  },
  computed: {
    ...mapState(['classroom'])
  }
}
</script>
<style scope>
.centerButton {
  margin-left: calc(50% - 20px);
  margin-top: 16%;
}
</style>
