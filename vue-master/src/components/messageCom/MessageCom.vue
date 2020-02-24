<template>
  <div>
    <wux-col style="display:flex;justify-content: center;">
      <wux-image wux-class="image"  width="60px" height="60px"  shape="circle" src="https://picsum.photos/750/750/?random&s=2" loading="Loading" />
    </wux-col>
    <wux-divider dashed text="消息" />
    <message-tap @handleChange="handleChange"></message-tap>
    <message-notice-bar v-bind:noticeGroup="noticeGroup" v-if="(currentSwiperComponent==='tab1')"></message-notice-bar>
    <message-swipe-out v-bind:commentGroup="commentGroup"  v-if="(currentSwiperComponent==='tab2')"></message-swipe-out>
    <chat-swipe-out v-bind:chatGroup="chatGroup" v-if="(currentSwiperComponent==='tab3')"></chat-swipe-out>
  </div>
</template>

<script>
  import MessageTap from './MessageTap'
  import MessageSwipeOut from './MessageSwipeOut'
  import MessageNoticeBar from './MessageNoticeBar'
  import ChatSwipeOut from './ChatSwipeOut'
  export default {
    components: {
      MessageTap,
      MessageSwipeOut,
      MessageNoticeBar,
      ChatSwipeOut
    },
    data () {
      return {
        currentSwiperComponent: 'tab3',
        noticeGroup: [{content: '2018年世界杯,将于6月14日至7月15日举行'}, {content: '2018年世界杯,将于6月14日至7月15日举行'}],
        commentGroup: [{username: '副校长', content: '下午开会', url: 'https://i.loli.net/2017/08/21/599a521472424.jpg', see: 'true'}, {username: '校长', content: '下午开会', url: 'https://i.loli.net/2017/08/21/599a521472424.jpg', see: 'true'}],
        chatGroup: [{username: '李老师', content: '下午开会', url: 'https://i.loli.net/2017/08/21/599a521472424.jpg'}, {username: '李老师', content: '下午开会', url: 'https://i.loli.net/2017/08/21/599a521472424.jpg'}]
      }
    },
    methods: {
      handleChange (value) {
        this.currentSwiperComponent = value
        // console.log(this.currentSwiperComponent)
        // if (this.currentSwiperComponent === 'tab3') {
        //   console.log(this.chatGroup)
        // }
      }
    }
  }
</script>

<style scoped>

</style>
