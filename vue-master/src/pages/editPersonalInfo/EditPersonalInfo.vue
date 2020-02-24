<template>
  <div>
    <wux-navbar theme="calm">
      <wux-icon slot="left" type="" size="23"/>
      <view>编辑个人信息</view>
      <wux-icon slot="right" type="ios-checkmark" size="23" @click="clickRight"/>
    </wux-navbar>
    <div class="container">
      <wux-select id="wux-select1" />
      <div class="avaterContainer">
        <wux-col span="4">
          <wux-image wux-class="image"  width="100px" height="100px" shape="circle" :src="avatarUrl" loading="Loading" />
        </wux-col>
    </div>
      <div class="inputContainer">
        <wux-cell-group title="请输入你的信息">
          <wux-cell hover-class="none">
            <wux-input clear label="姓名" placeholder="请输入真实姓名" controlled :value="name" @change="nameChange"/>
          </wux-cell>
          <wux-cell title="性别" :extra="title" :value="user.gender" @click="onClick1"></wux-cell>
          <wux-cell hover-class="none">
            <wux-input clear label="身份"  disabled controlled :value="identity" />
          </wux-cell>
          <wux-cell hover-class="none">
            <wux-input clear label="电话" controlled  disabled :value="user.phoneNumber" @change="phoneNumberChange"/>
          </wux-cell>
          <wux-cell hover-class="none">
            <wux-input clear label="邮箱" controlled placeholder="请输入你的常用邮箱" />
          </wux-cell>
        </wux-cell-group>
      </div>
    </div>
    <i-message id="message" />
  </div>
</template>

<script>
  import {$Message} from '../../../static/iview/base/index'
  import { $wuxSelect } from '../../../static/wux/index'
  import StorageUtil from '../../utils/StorageUtil'
  import {urlUtils} from '../../utils/urlUtils'
  export default {
    data () {
      return {
        identity: '',
        user: {},
        name: '',
        avatarUrl: '',
        title: ''
      }
    },
    methods: {
      clickRight () {
        if (this.name === '' || this.user.phoneNumber === '') {
          $Message({
            content: '请按照要求填写！',
            type: 'warning'
          })
          return
        }
        wx.showLoading({
          title: '加载中...',
          mask: true
        })
        let urlPlus = ''
        if (this.identity === '教师') {
          urlPlus = '/teacher/change'
        } else {
          urlPlus = '/student/change'
        }
        this.$fly.post(urlUtils + urlPlus, JSON.stringify(this.user), {headers: {'content-type': 'application/json'}})
          .then((response) => {
            const {data} = response
            console.log(data)
            if (data.code === 200) {
              $Message({
                content: '成功！',
                type: 'success'
              })
              StorageUtil.setUser(this.user)
              wx.reLaunch({
                url: '/pages/identification/main'
              })
            } else {
              $Message({
                content: '修改失败',
                type: 'error'
              })
            }
          }).catch(() => {
            $Message({
              content: '修改失败',
              type: 'error'
            })
          }).finally(() => {
            wx.hideLoading()
          })
      },
      nameChange (e) {
        this.name = e.mp.detail.value.trim()
        if (this.identity === '教师') {
          this.user.teaName = e.mp.detail.value.trim()
        } else {
          this.user.stuName = e.mp.detail.value.trim()
        }
      },
      phoneNumberChange (e) {
        this.user.phoneNumber = e.mp.detail.value.trim()
      },
      onClick1 () {
        $wuxSelect('#wux-select1').open({
          value: this.user.gender,
          options: [
            '男',
            '女',
            '其他'
          ],
          onConfirm: (value, index, options) => {
            if (index !== -1) {
              this.user.gender = value
              this.title = value
              this.title = options[index]
            }
          }
        })
      }
    },
    beforeMount () {
      let user = StorageUtil.getUser()
      this.user = user
      this.title = user.gender
      if (user.identity === 'teacher') {
        this.identity = '教师'
        this.avatarUrl = user.teaIcon
        this.name = user.teaName
      } else {
        this.identity = '学生'
        this.avatarUrl = user.stuIcon
        this.name = user.stuName
      }
    }
  }
</script>

<style scoped>
  .container{
    display: flex;
    flex-direction: column;
  }
</style>
