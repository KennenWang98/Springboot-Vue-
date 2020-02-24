<template>
  <div>
    <home v-if="identity === 'teacher'"></home>
    <student-home v-if="identity === 'student'"></student-home>
    <i-toast id="toast" />
    <i-message id="message" />
  </div>
</template>
<script>
import Home from '../../components/Home'
import StudentHome from '../../components/student/StudentHome'
import {$Toast, $Message} from '../../../static/iview/base/index'
import StorageUtil from '../../utils/StorageUtil'
export default {
  components: {
    Home,
    StudentHome
  },
  data () {
    return {
      identity: ''
    }
  },
  methods: {
    scanQrcode () {
      // 允许从相机和相册扫码
      wx.scanCode({
        success: res => {
          wx.showToast({
            title: '加入成功',
            icon: 'success',
            duration: 2000
          })
        },
        fail: res => {
          wx.showToast({
            title: '加入失败',
            icon: 'none',
            duration: 2000
          })
        }
      })
    }
  },
  beforeMount () {
    const userName = this.$mp.query.userName
    const user = StorageUtil.getUser()
    this.identity = user.identity
    if (userName === '') {
      wx.redirectTo({
        url: '/pages/editPersonalInfo/main'
      })
    } else {
      $Message({
        content: '欢迎使用！！',
        type: 'success'
      })
      $Toast({
        content: '加载中',
        type: 'loading'
      })
    }
  }
}
</script>
<style scope>
  .wrapper {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
  }
</style>
