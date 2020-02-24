<template>
  <div>
    <van-divider hairline />
    <wux-timeline>
      <div v-for="(homework, homeworkIndex) in assignedHomework" :key="homeworkIndex">
        <student-homework-timeline-item :homework="homework"></student-homework-timeline-item>
      </div>
    </wux-timeline>
  </div>
</template>
<script>
import StudentHomeworkTimelineItem from './StudentHomeworkTimelineItem'
import {mapState} from 'vuex'
export default {
  data () {
    return {
    }
  },
  components: {
    StudentHomeworkTimelineItem
  },
  computed: {
    ...mapState(['assignedHomework'])
  }
}
</script>
<style scope>
</style>
