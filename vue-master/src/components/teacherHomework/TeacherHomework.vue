<template>
  <div>
    <van-divider hairline />
    <wux-timeline>
      <div v-for="(homework, homeworkIndex) in assignedHomework" :key="homeworkIndex">
        <teacher-homework-timeline-item :homework="homework"></teacher-homework-timeline-item>
      </div>
    </wux-timeline>
  </div>
</template>
<script>
import TeacherHomeworkTimelineItem from './TeacherHomeworkTimelineItem'
import {mapState} from 'vuex'
export default {
  data () {
    return {
    }
  },
  components: {
    TeacherHomeworkTimelineItem
  },
  computed: {
    ...mapState(['assignedHomework'])
  }
}
</script>
<style scope>
</style>
