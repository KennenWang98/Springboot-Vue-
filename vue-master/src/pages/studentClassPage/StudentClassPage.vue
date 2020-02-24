<template>
  <div>
    <div>
      <wux-row>
        <wux-col span="5" push="1">
          <span>{{classRoom.className}}</span>
        </wux-col>
      </wux-row>
      <wux-grids col="2" :bordered="isFalse">
        <wux-grid thumb="/static/images/classroom/members.png" label="班级成员" @click="jumpToMembers"/>
        <wux-grid thumb="/static/images/classroom/microsphere.png" label="微圈" @click="jumpToMicrosphere"/>
      </wux-grids>
      <van-divider borderColor="#1989fa"/>
      <van-divider hairline />
      <wux-row >
        <wux-col span="4" push="1">
          <span>学习日志</span>
        </wux-col>
      </wux-row>
      <wux-dialog id="wux-dialog" />
      <wux-dialog id="wux-dialog--alert" />
    </div>
    <div>
      <i-tabs :current="currentKey" @change="handleChange">
        <i-tab key="courseware" title="学习资料"></i-tab>
        <i-tab key="homework" title="作业"></i-tab>
        <i-tab key="ranking" title="排名"></i-tab>
        <i-tab key="bulletin" title="公告"></i-tab>
      </i-tabs>
    </div>
    <div>
      <not-something v-if="currentKey==='courseware'|| currentKey==='bulletin'"></not-something>
      <student-homework v-if="currentKey==='homework'"></student-homework>
      <teacher-ranking v-if="currentKey==='ranking'"></teacher-ranking>
    </div>
  </div>
</template>
<script>
import NotSomething from '../../components/NotSomething'
import StudentHomework from '../../components/studentHomework/StudentHomework'
import TeacherRanking from '../../components/teacherRanking/TeacherRanking'
import {mapState} from 'vuex'
export default {
  data () {
    return {
      classRoom: {},
      isFalse: false,
      currentKey: 'courseware',
      show: false,
      schoolIndex: null,
      classIndex: null
    }
  },
  methods: {
    jumpToMicrosphere () {
      const classId = this.classroom.classId
      this.$store.dispatch('setMicrospherePictures', classId)
      wx.navigateTo({
        url: '/pages/teacherMicrosphere/main'
      })
    },
    jumpToMembers () {
      wx.navigateTo({
        url: '/pages/studentClassMembers/main?classId=' + this.classRoom.classId
      })
    },
    handleChange (event) {
      if (event.mp.detail.key === 'ranking') {
        const classId = this.classroom.classId
        this.$store.dispatch('getAllStudentRank', classId)
      }
      this.currentKey = event.mp.detail.key
    },
    showPublicTab () {
      this.show = true
    },
    onClose () {
      this.show = false
    }
  },
  beforeMount () {
    this.schoolIndex = this.$mp.query.schoolIndex
    this.classIndex = this.$mp.query.classIndex
    this.classRoom = this.schoolList[this.schoolIndex].classList[this.classIndex]
    const classroom = this.classRoom
    this.$store.dispatch('setClassroom', classroom)
    this.$store.dispatch('initStudentList', this.classRoom.classId)
    const teaId = this.classroom.teaId
    const classId = this.classroom.classId
    this.$store.dispatch('initAssignedHomework', {teaId, classId})
  },
  components: {
    NotSomething,
    StudentHomework,
    TeacherRanking
  },
  computed: {
    ...mapState(['schoolList', 'classroom'])
  }
}
</script>
<style scope>
</style>
