<template>
  <div>
    <van-cell-group>
    <van-field :value="licenseId" @change="licenseIdChange"required clearable label="许可码" placeholder="请输入许可码"/>
    <van-field :value="className" @change="classNameChange" label="班级名称" placeholder="请输入班级名称" required />
    <van-field :value="schoolName" @change="schoolNameChange" label="学校名称" placeholder="请输入所属学校" required />
    <van-field :value="classText" @change="classTextChange" type="textarea" label="班级简介" placeholder="在这里介绍一下你的班级吧" />
    </van-cell-group>
    <wux-actionsheet id="wux-actionsheet" />
      <view class="page__bd page__bd_spacing">
        <wux-button block type="light" @click="showActionSheet">创建班级</wux-button>
      </view>
    <i-message id="message" />
  </div>
</template>
<script>
import {$Message} from '../../../static/iview/base/index'
import {$wuxActionSheet} from '../../../static/wux/index'
import {urlUtils} from '../../utils/urlUtils'
export default {
  data () {
    return {
      licenseId: '',
      className: '',
      classText: '',
      schoolName: ''
    }
  },
  computed: {
  },
  methods: {
    onSelectTap (event) {
    },
    onClose () {
      this.visible = false
    },
    licenseIdChange (e) {
      this.licenseId = e.mp.detail.trim()
    },
    classNameChange (e) {
      this.className = e.mp.detail.trim()
    },
    schoolNameChange (e) {
      this.schoolName = e.mp.detail.trim()
    },
    classTextChange (e) {
      this.classText = e.mp.detail.trim()
    },
    showActionSheet () {
      $wuxActionSheet().showSheet({
        theme: 'wx',
        titleText: '确定创建你的班级吗？',
        buttons: [{
          text: '确认'
        }
        ],
        buttonClicked: (index, item) => {
          if (this.licenseId === '' || this.schoolName === '' || this.className === '') {
            $Message({
              content: '请按照要求填写！',
              type: 'warning'
            })
            return
          }
          const phoneNumber = this.$store.state.userInformation.phoneNumber
          this.$fly.get(urlUtils + '/class/add', {
            code: this.licenseId,
            className: this.className,
            schoolName: this.schoolName,
            classText: this.classText,
            phoneNumber: phoneNumber,
            classIcon: 'https://picsum.photos/750/750/?random&s=2'
          })
            .then(response => {
              const {data} = response
              console.log(data)
              if (data.code !== 200) {
                $Message({
                  content: data.msg,
                  type: 'error'
                })
                return
              }
              this.licenseId = ''
              this.schoolName = ''
              this.className = ''
              this.classText = ''
              this.$store.dispatch('updateSchoolList', data.data)
              $Message({
                content: '创建成功',
                type: 'success'
              })
            })
            .catch(() => {
              $Message({
                content: '失败！',
                type: 'error'
              })
            })
          return true
        },
        cancelText: '取消',
        cancel () {}
      })
    }
  }
}
</script>
<style scope>
</style>
