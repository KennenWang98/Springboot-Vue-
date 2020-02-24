<template>
  <div>
    <wux-navbar theme="dark"><view>题目</view></wux-navbar>
    <problem-view-com :homework="homework"></problem-view-com>
    <wux-button block type="calm" v-if="showCommit" @click="submit">提交</wux-button>
    <stu-pagination @onPageChange="onPageChange" :total="total" :page-current="pageCurrent"></stu-pagination>
    <wux-dialog id="wux-dialog" />
    <wux-dialog id="wux-dialog--alert" />
    <i-message id="message"></i-message>
  </div>
</template>
<script>
  import StuTap from '../../components/StuTap'
  import StuPagination from '../../components/StuPagination'
  import ProblemViewCom from '../../components/studentProblemsCom/ProblemViewCom'
  import {mapState} from 'vuex'
  import StorageUtil from '../../utils/StorageUtil'
  import {$wuxDialog} from '../../../static/wux/index'
  export default {
    components: {
      StuTap,
      StuPagination,
      ProblemViewCom
    },
    data () {
      return {
        homework: {},
        total: null,
        showCommit: false,
        pageCurrent: 1
      }
    },
    methods: {
      onPageChange (pageCurrent) {
        this.homework = this.homeworkDetail[pageCurrent - 1]
        this.pageCurrent = pageCurrent
        if (pageCurrent === this.total) {
          this.showCommit = true
        } else {
          this.showCommit = false
        }
      },
      submit () {
        $wuxDialog().open({
          resetOnClose: true,
          title: '提交',
          content: '提交后将无法修改，确定提交吗',
          buttons: [{
            text: '取消'
          },
          {
            text: '确定',
            type: 'primary',
            onTap: (e) => {
              const answer = this.answer
              const stuAnswerContentVOs = this.stuAnswer
              const stuPhoneNumber = StorageUtil.getUser().phoneNumber
              this.$store.dispatch('submitAnswers', {answer, stuAnswerContentVOs, stuPhoneNumber})
            }
          }
          ]
        })
      }
    },
    beforeMount () {
      this.homework = this.homeworkDetail[0]
      this.total = this.homeworkDetail.length
      this.pageCurrent = 1
      if (this.total === 1) {
        this.showCommit = true
      }
    },
    computed: {
      ...mapState(['homeworkDetail', 'answer', 'stuAnswer'])
    }
  }
</script>
<style scoped>
</style>
