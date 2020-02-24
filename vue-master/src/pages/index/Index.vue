<template>
  <div>
    <div class="backContent">
      <!--pics文件夹下的background.jpg文件-->
      <img class='background' src="../../../static/images/weixin/loginBackground.jpeg" mode="aspectFill " />
      <!--页面其它部分-->
    </div>
    <div class="container">
      <div class="imgContainer">
        <van-image
          round
          width="5rem"
          height="5rem"
          src="/static/images/weixin/login.png"
        />
      </div>
      <br>
      <div class="spanContent" v-if="showLogin">
        <span class="content">申请获取以下权限</span>
      </div>
      <br>
      <div class="spanContent" v-if="showLogin">
        <span class="content" >获得你的公开信息（昵称， 头像等）</span>
      </div>
      <br>
      <div class="buttonContainer">
        <wux-actionsheet id="wux-actionsheet" />
        <view class="page__bd page__bd_spacing">
          <wux-button block open-type="getPhoneNumber" type="balanced" @getphonenumber="getPhoneNumber" v-if="showLogin" >授权登录</wux-button>
        </view>
      </div>
    </div>
  </div>
</template>
<script>
import {$wuxActionSheet} from '../../../static/wux/index'
import StorageUtil from '../../utils/StorageUtil'
import {urlUtils} from '../../utils/urlUtils'
export default {
  data () {
    return {
      showLogin: false,
      logInfo: null,
      identity: '',
      nickName: '',
      avatarUrl: 'https://picsum.photos/750/750/?random&s=2',
      gender: ''
    }
  },
  methods: {
    getPhoneNumber (e) {
      $wuxActionSheet().showSheet({
        theme: 'wx',
        titleText: '请选择你的身份',
        buttons: [{
          text: '教师'
        },
        {
          text: '学生'
        }],
        buttonClicked: (index) => {
          wx.showLoading({
            title: '登录中...',
            mask: true
          })
          if (index === 0) {
            wx.login({
              success: res => {
                this.teacherLogin(e, res)
              },
              complete: () => {
                wx.hideLoading()
              }
            })
          }
          if (index === 1) {
            wx.login({
              success: res => {
                this.studentLogin(e, res)
              },
              complete: () => {
                wx.hideLoading()
              }
            })
          }
          return true
        },
        cancelText: '取消',
        cancel () {
        }
      })
    },
    teacherLogin (e, res) {
      wx.request({
        url: urlUtils + '/teacher/deciphering',
        data: {
          'encryptedData': e.mp.detail.encryptedData,
          'iv': e.mp.detail.iv,
          'code': res.code,
          'identity': 'teacher',
          'teaName': this.nickName,
          'teaIcon': this.avatarUrl,
          'gender': this.gender
        },
        method: 'GET',
        header: {
          'content-type': 'application/json'
        },
        success: res => {
          const {data} = res
          if (data.code === 200) {
            const user = {
              phoneNumber: data.data.userInfo.phoneNumber,
              identity: 'teacher',
              teaName: data.data.name,
              teaIcon: data.data.icon,
              gender: data.data.gender
            }
            StorageUtil.setUser(user)
            this.$store.dispatch('set_user_information', user)
            this.$store.dispatch('init_school_list', data.data.schoolList || [])
            wx.redirectTo({
              url: `/pages/identification/main?userName=${user.teaName}`
            })
          }
        },
        fail: () => {
          console.log('请求失败')
        }
      })
    },
    studentLogin (e, res) {
      wx.request({
        url: urlUtils + '/teacher/deciphering',
        data: {
          'encryptedData': e.mp.detail.encryptedData,
          'iv': e.mp.detail.iv,
          'code': res.code,
          'identity': 'student',
          'teaName': this.nickName,
          'teaIcon': this.avatarUrl,
          'gender': this.gender
        },
        method: 'GET',
        header: {
          'content-type': 'application/json'
        },
        success: res => {
          const {data} = res
          if (data.code === 200) {
            const user = {
              phoneNumber: data.data.userInfo.phoneNumber,
              identity: 'student',
              stuName: data.data.name,
              stuIcon: data.data.icon,
              gender: data.data.gender
            }
            StorageUtil.setUser(user)
            this.$store.dispatch('set_user_information', user)
            this.$store.dispatch('init_school_list', data.data.schoolList || [])
            wx.redirectTo({
              url: `/pages/identification/main?userName=${user.stuName}`
            })
          }
        },
        fail: () => {
          console.log('请求失败')
        }
      })
    }
  },
  beforeMount () {
    let user = StorageUtil.getUser()
    if (user.phoneNumber) {
      this.$store.dispatch('set_user_information', user)
      this.$fly.get(urlUtils + '/teacher/login', {
        phoneNumber: user.phoneNumber,
        identity: user.identity
      })
        .then(response => {
          const {data} = response
          if (data.code === 200) {
            this.$store.dispatch('init_school_list', data.data)
            if (user.identity === 'teacher') {
              wx.redirectTo({
                url: '/pages/identification/main?userName=' + user.teaName
              })
            } else {
              wx.redirectTo({
                url: '/pages/identification/main?userName=' + user.stuName
              })
            }
          }
        })
        .catch(error => {
          console.log(error)
        })
    } else {
      this.showLogin = true
    }
  }
}
</script>
<style scoped>
  .backContent{
    width: 100%;
    height: 100%;
  }
  .background {
    width: 100%;
    height: 100%;
    position:fixed;
    background-size:100% 100%;
    filter:alpha(opacity=50);
    -moz-opacity:0.5;
    -khtml-opacity: 0.5;
    opacity: 0.5;
    z-index: -1;
  }
  .container  {
    width: 100%;
    height: 90%;
    display: flex;
    flex-direction: column;
    margin-left: 0;
    /*margin-top: 10%;*/
  }
  .imgContainer {
    display: flex;
    margin-left: 0;
    margin-bottom: 5%;
  }
  .spanContent  {
    display: flex;
    margin-bottom: 2%;

  }
  .buttonContainer  {
    display: flex;
    margin-top: 5%;

  }
  .content {
    text-align:center
  }
</style>
