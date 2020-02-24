<template>
  <div>
    <i-card :title="name" :thumb="icon">
      <i-cell slot="footer" title="个人资料编辑" is-link url="/pages/editPersonalInfo/main" link-type="navigateTo"></i-cell>
    </i-card>
    <wux-cell-group title="个人设置">
      <wux-cell thumb="https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/wh%3D600%2C800/sign=4d21c36235f33a879e38081cf66c3c00/29381f30e924b8993119396a61061d950a7bf6bf.jpg" title="我的E-learning" is-link  @contact="onContact"></wux-cell>
      <wux-cell thumb="https://gss0.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/zhidao/wh%3D600%2C800/sign=6d1ebb799216fdfad839cee884bfa06c/eaf81a4c510fd9f91ddb153b2a2dd42a2934a490.jpg" title="我的收藏" is-link @getuserinfo="onGotUserInfo"></wux-cell>
      <wux-cell thumb="https://gss0.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/zhidao/wh%3D600%2C800/sign=c5e6c57e8801a18bf0be1a49ae1f2b30/8694a4c27d1ed21b40c66db7a26eddc451da3f50.jpg" title="绑定手机" is-link open-type="getPhoneNumber" @getphonenumber="onGotPhoneNumber"></wux-cell>
      <wux-cell thumb="https://gss0.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/267f9e2f0708283889d49d46b799a9014d08f183.jpg" title="联系客服" is-link open-type="contact" @getphonenumber="onGotPhoneNumber"></wux-cell>
      <wux-cell thumb="https://gss0.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/d52a2834349b033b8960bd881ace36d3d439bdfc.jpg" title="帮助" is-link open-type="contact"></wux-cell>
    </wux-cell-group>
  </div>
</template>
<script>
  import StorageUtil from '../../utils/StorageUtil'
  export default {
    data () {
      return {
        name: '',
        icon: ''
      }
    },
    methods: {
      onChange (event) {
        this.active = event.mp.detail
        this.$emit('onChange', this.active)
      },
      onContact (e) {
        console.log('onContact', e)
      },
      onGotUserInfo (e) {
        console.log('onGotUserInfo', e)
      },
      onGotPhoneNumber (e) {
        console.log('onGotPhoneNumber', e)
      }
    },
    beforeMount () {
      const user = StorageUtil.getUser()
      if (user.identity === 'teacher') {
        this.icon = user.teaIcon
        this.name = user.teaName
      } else {
        this.icon = user.stuIcon
        this.name = user.stuName
      }
    }
  }
</script>

<style scoped>

</style>
