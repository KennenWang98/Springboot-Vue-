<template>
  <wux-timeline-item custom>
    <wux-icon :hidden="timeLineItem.timelineStatus" slot="dot" type="ios-time" size="16" />
    <wux-icon :hidden="!timeLineItem.timelineStatus" slot="dot" type="ios-radio-button-on" size="16" color="#778866"/>
    <view>布置：{{homework.layoutTimeString}}<wux-button size="small" type="assertive" style="margin-left: 50px" clear @click="onDelete">删除</wux-button></view>
    <wux-card bordered :title="homework.classHomework.homeworkName" :actions="timeLineItem.actions" @action="onAction" :extra="'截止：' + homework.deadlineString">
      <view slot="body">学生完成度</view>
      <wux-progress slot="body" show-info :percent="homework.percentage" />
      <view slot="footer">状态：{{homework.status}}</view>
    </wux-card>
  </wux-timeline-item>
</template>
<script>
import {mapState} from 'vuex'
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
          text: '查看详情',
          disabled: false
        }, {
          type: 'primary',
          text: '学生成绩',
          disabled: false
        }],
        percent: 25
      }
    }
  },
  computed: {
    ...mapState(['classroom'])
  },
  methods: {
    onAction (e) {
      if (e.mp.detail.action.text === '查看详情') {
        const chId = this.homework.classHomework.chId
        const status = '教师查看'
        this.$store.dispatch('setHomeworkDetail', {chId, status})
      } else {
        const classId = this.classroom.classId
        const chId = this.homework.classHomework.chId
        this.$store.dispatch('getStudentRank', {classId, chId})
      }
    },
    onDelete () {
      const chId = this.homework.classHomework.chId
      this.$store.dispatch('deleteAssignedHomework', chId)
    }
  },
  beforeMount () {
    const status = this.homework.classHomework.status
    if (status === '未批改') {
      this.timeLineItem.timelineStatus = false
    } else {
      this.timeLineItem.timelineStatus = true
      this.timeLineItem.actions[1].disabled = true
    }
    if (this.homework.status === '已截止') {
      this.timeLineItem.actions[1].disabled = false
      this.timeLineItem.timelineStatus = true
    } else {
      this.timeLineItem.actions[1].disabled = true
    }
  }
}
</script>
<style scope>
</style>
