<template>
  <div>
    <div>
      <wux-row>
        <wux-col span="5" push="1">
          <span>{{classRoom.className}}</span>
        </wux-col>
        <wux-col span="5" push="5">
          <wux-qrcode id="qrcode" wux-class="qrcode" :data="classRoom.classQrCode" width="50" height="50" @click="previewImage" />
        </wux-col>
      </wux-row>
      <wux-grids col="4" :bordered="isFalse">
        <wux-grid thumb="/static/images/classroom/members.png" label="成员管理" @click="jumpToMembers"/>
        <wux-grid thumb="/static/images/classroom/homework.png" label="题库" @click="jumpToQuestionBank"/>
        <wux-grid thumb="/static/images/classroom/microsphere.png" label="微圈" @click="jumpToMicrosphere"/>
        <wux-grid thumb="/static/images/classroom/setting.png" label="设置" @click="jumpToTeacherClassSetting" />
      </wux-grids>
      <van-divider borderColor="#1989fa"/>
      <van-divider hairline />
      <wux-row >
        <wux-col span="4" push="1">
          <span>教学日志</span>
        </wux-col>
        <wux-col span="4" push="4">
          <i-button size="small" @click="vertical">发布</i-button>
        </wux-col>
      </wux-row>
      <wux-dialog id="wux-dialog" />
      <wux-dialog id="wux-dialog--alert" />
    </div>
    <div>
      <i-tabs :current="currentKey" @change="handleChange">
        <i-tab key="courseware" title="课件"></i-tab>
        <i-tab key="homework" title="作业"></i-tab>
        <i-tab key="ranking" title="排名"></i-tab>
        <i-tab key="bulletin" title="公告"></i-tab>
      </i-tabs>
    </div>
    <div>
      <not-something v-if="currentKey==='courseware'|| currentKey==='bulletin'"></not-something>
      <teacher-homework v-if="currentKey==='homework'"></teacher-homework>
      <teacher-ranking v-if="currentKey==='ranking'"></teacher-ranking>
    </div>
    <i-message id="message"></i-message>
  </div>
</template>
<script>
import {$wuxDialog} from '../../../static/wux/index'
import NotSomething from '../../components/NotSomething'
import TeacherHomework from '../../components/teacherHomework/TeacherHomework'
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
    previewImage (event) {
      // 在自定义组件下，当前组件实例的 this，以操作组件内 <canvas> 组件
      const that = this.$mp.page.selectComponent('#qrcode')
      wx.canvasToTempFilePath({
        canvasId: 'wux-qrcode',
        success: (res) => {
          wx.previewImage({
            urls: [res.tempFilePath]
          })
        }
      }, that)
    },
    jumpToMicrosphere () {
      const classId = this.classroom.classId
      this.$store.dispatch('setMicrospherePictures', classId)
      wx.navigateTo({
        url: '/pages/teacherMicrosphere/main'
      })
    },
    jumpToMembers () {
      wx.navigateTo({
        url: '/pages/manegementClassMembers/main?classId=' + this.classRoom.classId
      })
    },
    handleChange (event) {
      if (event.mp.detail.key === 'ranking') {
        const classId = this.classroom.classId
        this.$store.dispatch('getAllStudentRank', classId)
      }
      this.currentKey = event.mp.detail.key
    },
    jumpToQuestionBank () {
      const teaId = this.classRoom.teaId
      const classId = this.classRoom.classId
      const isView = false
      const isJump = true
      this.$store.dispatch('initQuestion', {teaId, classId, isView, isJump})
    },
    showPublicTab () {
      this.show = true
    },
    onClose () {
      this.show = false
    },
    vertical () {
      $wuxDialog().open({
        resetOnClose: true,
        title: '发布',
        content: '您可以发布以下信息哦！',
        verticalButtons: !0,
        buttons: [{
          text: '作业',
          bold: !0,
          onTap: (event) => {
            const teaId = this.classRoom.teaId
            const classId = this.classRoom.classId
            const isView = true
            const isJump = true
            this.$store.dispatch('initQuestion', {teaId, classId, isView, isJump})
          }
        },
        {
          text: '公告',
          bold: !0,
          onTap (event) {
            console.log('发布公告')
          }
        },
        {
          text: '课件',
          bold: !0,
          onTap (event) {
            console.log('发布课件')
          }
        }
        ]
      })
    },
    jumpToTeacherClassSetting () {
      wx.navigateTo({
        url: `/pages/teacherClassSetting/main?schoolIndex=${this.schoolIndex}&classIndex=${this.classIndex}`
      })
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
    TeacherHomework,
    TeacherRanking
  },
  computed: {
    ...mapState(['schoolList', 'classroom'])
  }
}
</script>
<style scope>
</style>
