<template>
  <wux-timeline-item custom>
    <wux-icon :hidden="timeLineItem.timelineStatus" slot="dot" type="ios-time" size="16" />
    <wux-icon :hidden="!timeLineItem.timelineStatus" slot="dot" type="ios-radio-button-on" size="16" />
    <view>布置：{{homework.layoutTimeString}}</view>
    <wux-card bordered :title="homework.classHomework.homeworkName" :actions="timeLineItem.actions" @action="onAction" :extra="'截止：' + homework.deadlineString">
      <view slot="footer">状态：{{homework.status}}</view>
    </wux-card>
  </wux-timeline-item>
</template>
<script>
export default {
  props: {
    homework: Object
  },
  data () {
    return {
      timeLineItem: {
        timelineStatus: false,
        actions: [{
          type: 'default',
          text: '查看答案',
          disabled: true
        }, {
          type: 'primary',
          text: '去作答',
          disabled: false
        }],
        percent: 25
      }
    }
  },
  methods: {
    onAction (e) {
      if (e.mp.detail.action.text === '去作答') {
        const chId = this.homework.classHomework.chId
        const status = '去作答'
        this.$store.dispatch('setHomeworkDetail', {chId, status})
        this.$store.dispatch('setAnswer', chId)
      } else {
        const chId = this.homework.classHomework.chId
        const status = '教师查看'
        this.$store.dispatch('setHomeworkDetail', {chId, status})
      }
    }
  },
  beforeMount () {
    if (this.homework.status === '已截止') {
      this.timeLineItem.actions[1].disabled = true
      this.timeLineItem.actions[0].disabled = false
      this.timeLineItem.timelineStatus = false
    } else {
      this.timeLineItem.timelineStatus = true
      this.timeLineItem.actions[1].disabled = false
      this.timeLineItem.actions[0].disabled = true
    }
  }
}
</script>
<style scope>
</style>
