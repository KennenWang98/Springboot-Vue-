<template>
  <div>
    <wux-wing-blank size="default">
      <wux-card title="判断题录入" extra="判断题">
        <div slot="body">
          <wux-cell hover-class="none">
            <wux-textarea  rows="4" :value="proContentvalue"  placeholder="请输入此题题干" controlled  @change="onProContentChange" @clear="onClear"/>
          </wux-cell>
          <i-divider content="答案" color="#2d8cf0" lineColor="#2d8cf0"></i-divider>
          <van-radio-group :value="radio" @change="onJugementChange">
            <van-radio name="1">对</van-radio>
            <i-divider color="#2d8cf0" lineColor="#2d8cf0"></i-divider>
            <van-radio name="2">错</van-radio>
          </van-radio-group>
          <i-divider content="解析" color="#2d8cf0" lineColor="#2d8cf0"></i-divider>
          <wux-cell hover-class="none">
            <wux-textarea rows="4"  clear controlled :value="solutionText" cursorSpacing="80" placeholder="请输入此题解析" type="text" @change="getComment"/>
          </wux-cell>
          <button class='goodbutton'  hover-class="hover" @click="onSubmit">提交</button>
        </div>
      </wux-card>
    </wux-wing-blank>
    <i-message id="message"></i-message>
  </div>
</template>

<script>
  import {mapState} from 'vuex'
  export default {
    data () {
      return {
        quesContent: '', /// 题目题干
        radio: '', /// 判断题当前默认选中选项
        solutionText: ''
      }
    },
    methods: {
      onProContentChange (e) { /// 获得老师输入的题干
        this.quesContent = e.mp.detail.value.trim()
      },
      onJugementChange (e) { /// 判断题作用函数，这里是单选功能的实现和单选题实现差不多
        this.radio = e.mp.detail
      },
      getComment (e) {
        this.solutionText = e.mp.detail.value /// 获得输入的解析
      },
      onSubmit (e) { /// 在这里传值
        let ready = true
        const teaId = this.classroom.teaId
        const classId = this.classroom.classId
        const teaQuesType = '判断题'
        const teacherQuestions = {teaId, classId, teaQuesType}
        const quesContent = this.quesContent
        const isTrue = this.radio === '1' ? 1 : 0
        const solutionText = this.solutionText
        const teaJudge = {quesContent, isTrue, solutionText}
        const addProblem = {teacherQuestions, teaJudge}
        if (quesContent === '') {
          wx.showModal({
            title: '题目不能为空',
            showCancel: !1
          })
          ready = false
        }
        if (this.radio === '') {
          wx.showModal({
            title: '答案不能为空',
            showCancel: !1
          })
          ready = false
        }
        if (ready) {
          this.$store.dispatch('addProblem', addProblem)
        }
      }
    },
    computed: {
      ...mapState(['classroom'])
    }
  }
</script>

<style scoped>
  .goodbutton {
    margin-top: 30px;
    margin-left: 15%;
    width: 60%;
    height: 30px;
    font-size: 15px;
    background-color: deepskyblue;
    color: white;
    -webkit-border-radius: 98px;
    -moz-border-radius: 98px;
    border-radius: 5px;
    text-align: center;
  }
  .buttonContainer {
    display: flex;
    flex-direction: row;
    margin-left: 0;
    justify-content:center;
    align-items:center;
  }
  .buttonContainer button{

  }
  /* 按下变颜色 */
  .hover {
    background-color: dodgerblue;
  }
</style>
