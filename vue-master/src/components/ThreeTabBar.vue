<template>
  <van-tabbar :active="active" @change="onChange">
  <van-tabbar-item>
    <image
      slot="icon"
      src="/static/images/tabBar/NScourse.png"
      mode="aspectFit"
      style="width: 30px; height: 18px;"
    />
    <image
      slot="icon-active"
      src="/static/images/tabBar/course.png"
      mode="aspectFit"
      style="width: 30px; height: 18px;"
    />课程
  </van-tabbar-item>
  <van-tabbar-item >
    <image
      slot="icon"
      src="/static/images/tabBar/NSmessage.png"
      mode="aspectFit"
      style="width: 30px; height: 18px;"
    />
    <image
      slot="icon-active"
      src="/static/images/tabBar/message.png"
      mode="aspectFit"
      style="width: 30px; height: 18px;"
    />消息
  </van-tabbar-item>
  <van-tabbar-item>
    <image
      slot="icon"
      src="/static/images/tabBar/NSmy.png"
      mode="aspectFit"
      style="width: 30px; height: 18px;"
    />我的
    <image
      slot="icon-active"
      src="/static/images/tabBar/my.png"
      mode="aspectFit"
      style="width: 30px; height: 18px;"
    />
  </van-tabbar-item>

  </van-tabbar>
</template>
<script>
export default {
  data () {
    return {
      active: 0
    }
  },
  methods: {
    onChange (event) {
      this.active = event.mp.detail
      this.$emit('onChange', this.active)
    }
  }
}
</script>
<style scope>
</style>
