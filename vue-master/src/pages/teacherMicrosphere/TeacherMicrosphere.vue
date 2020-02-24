<template>
  <div>
    <van-button color="linear-gradient(to right, #4bb0ff, #6149f6)" size="large" @click="showUploadImage" >发布微圈</van-button>
    <van-popup :show="showUpload" closeable position="bottom" custom-style="height: 70%" @close="onClose">
      <upload-images @UploadImageDoing="onClose"></upload-images>
    </van-popup>
    <div>
      <wux-timeline>
        <van-divider contentPosition="left">班级微圈</van-divider>
        <div v-for="(picture, index1) in microspherePictures" :key="index1">
          <wux-timeline-item :content="picture.name+'：'+picture.time" >
            <wux-skeleton active>
              <wux-row>
                <wux-col span="2">
                  <i-avatar src="https://picsum.photos/750/750/?random&s=2" size="default" v-if="true"></i-avatar>
                  <wux-skeleton-avatar v-else/>
                </wux-col>
                <wux-col span="10">
                  <wux-grids v-if="true">
                    <div v-for="(link, index) in picture.pictureLink" :key="index">
                      <wux-grid :thumb="link"/>
                    </div>
                  </wux-grids>
                  <wux-skeleton-paragraph v-else/>
                </wux-col>
              </wux-row>
            </wux-skeleton>
          </wux-timeline-item>
        </div>
      </wux-timeline>
    </div>
    <i-message id="message"></i-message>
  </div>
</template>
<script>
import UploadImages from '../../components/UploadImages'
import {mapState} from 'vuex'
export default {
  data () {
    return {
      showUpload: false
    }
  },
  methods: {
    showUploadImage () {
      this.showUpload = true
    },
    onClose () {
      this.showUpload = false
    }
  },
  components: {
    UploadImages
  },
  computed: {
    ...mapState(['microspherePictures'])
  }
}
</script>
<style scope>
</style>
