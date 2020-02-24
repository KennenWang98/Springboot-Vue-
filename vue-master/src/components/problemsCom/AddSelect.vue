<template>
  <div class="problemsContainer">
    <wux-wing-blank size="default">
      <wux-card title="选择题录入" :extra="extra">
        <div slot="body">
          <wux-select id="wux-select1" />
          <wux-cell-group title="选择题类型"><wux-cell title="请输入你需要录入的选择题类型" :extra="teaQuesType"  @click="onClick1"></wux-cell></wux-cell-group>
          <wux-cell hover-class="none">
            <wux-input controlled label="选项个数" clear autoHeight :value="NumberOfOptions"  type="number" @confirm="optionNumbersConfirm" @change="optionNumbersConfirm" @clear="onClear" />
          </wux-cell>
          <wux-cell hover-class="none">
            <wux-textarea rows="4"  clear controlled :value="quesValue" cursorSpacing="80" placeholder="请输入题干" type="text" @confirm="onConfirm" @change="onConfirm"/>
          </wux-cell>
          <wux-cell hover-class="none" v-for="select in selectGroup" :key="index">
            <wux-input :id="index" controlled :label="select.lable" clear  :placeholder="select.placeholder" :value="select.choiceContent" @change="optionValueChange" @confirm="optionValueChange"/>
          </wux-cell>
          <wux-cell hover-class="none">
            <wux-input controlled label="正确答案" clear  placeholder="多选题答案选项以英文逗号分隔" type="text" @change="correctOptionsConfirm"></wux-input>
          </wux-cell>
          <wux-cell hover-class="none">
            <wux-textarea rows="4"  clear controlled :value="solutionText" cursorSpacing="80" placeholder="请输入此题解析" type="text" @change="onCommentChange"/>
          </wux-cell>
          <button class='goodbutton'  hover-class="hover" @click="onSumbmit">提交</button>
        </div>
      </wux-card>
    </wux-wing-blank>
    <i-message id="message"></i-message>
  </div>
</template>

<script>
  import { $wuxSelect } from '../../../static/wux/index.js'
  import {mapState} from 'vuex'
  export default {
    data () {
      return {
        NumberOfOptions: '', /// 选项个数
        selectGroup: [], /// 选项的数组
        extra: '', /// 右边根据用户输入动态显示的题型
        solutionText: '', /// 解析
        quesContent: '', /// 题目
        teaQuesType: '', /// 题目类型
        answers: '',
        selectType: ''
      }
    },
    methods: {
      optionNumbersChange (e) { /// 选项个数改变时让之前动态生成的输入框消失
        this.selectGroup = []
      },
      optionValueChange (e) {
        const index = parseInt(e.currentTarget.id)
        this.selectGroup[index].choiceContent = e.mp.detail.value.trim()
      },
      onCommentChange (e) {
        this.solutionText = e.mp.detail.value.trim()
      },
      correctAnswerChange (e) {
        console.log(e.mp.detail.value)/// 正确答案
      },
      optionNumbersConfirm (e) { // 输入选项个数时动态生成数组的函数
        this.NumberOfOptions = e.mp.detail.value
        if (this.NumberOfOptions > 6) { /// 用户输入选项个数大于6，弹出警告
          wx.showModal({
            title: '请输入一个小于6的数字，这里规定选项个数不可以大于6',
            showCancel: !1
          })
        } else if (this.NumberOfOptions <= 0) { /// 用户输入选项个数小于等于0，弹出警告
          wx.showModal({
            title: '请输入一个大于0的数字',
            showCancel: !1
          })
        } else {
          let option = 65
          this.selectGroup = []
          for (let i = 0; i < this.NumberOfOptions; i++) {
            this.selectGroup.push({lable: String.fromCharCode(option), placeholder: '请输入' + String.fromCharCode(option) + '选项', choiceContent: '', isTrue: 0})
            option++
          }
        }
      },
      optionValueConfirm (e) { // 将用户输入的value填入上面那个函数生成的数组对应的value属性，此时，数组是完整的包含了用户输入
        const index = parseInt(e.currentTarget.id)
        this.selectGroup[index].choiceContent = e.mp.detail.value.trim()
      },
      onConfirm (e) { // 监测题干输入
        this.quesContent = e.mp.detail.value.trim()
      },
      onClear (e) { /// 清空输入的函数
        this.NumberOfOptions = null
        this.selectGroup = []
      },
      onSumbmit () { /// 教师输入完毕，点击按钮，提交所有输入
        let correctAnsNums = this.answers.split(',').length
        const correctAns = this.answers.split(',')
        if (correctAnsNums < 2 && this.selectType === '多选题') {
          wx.showModal({
            title: '请注意，您选择录入的是多选题哦~ ',
            showCancel: !1
          })
        } else if (correctAnsNums !== 1 && this.selectType === '单选题') {
          wx.showModal({
            title: '请注意，您选择录入的是单选题哦~ ',
            showCancel: !1
          })
        } else {
          correctAns.forEach((cur) => {
            let index = cur.charCodeAt() - 65
            if (index > this.NumberOfOptions) {
              wx.showModal({
                title: '输入的答案不合理，请修改后提交',
                showCancel: !1
              })
            }
            this.selectGroup[index].isTrue = 1
          })
          const teaId = this.classroom.teaId
          const classId = this.classroom.classId
          const teaQuesType = this.teaQuesType
          const teacherQuestions = {teaId, classId, teaQuesType}
          const quesContent = this.quesContent
          const teaChoiceQuestion = {quesContent}
          const teaChoiceSolutions = this.selectGroup
          const addProblem = {teacherQuestions, teaChoiceSolutions, teaChoiceQuestion}
          let ready = true
          if (teaQuesType === '') {
            wx.showModal({
              title: '请选择选择题的题型',
              showCancel: !1
            })
            ready = false
          }
          if (quesContent === '') {
            wx.showModal({
              title: '题目或选项不能为空',
              showCancel: !1
            })
            ready = false
          }
          console.log(this.selectGroup)
          const empty = this.selectGroup.find(cur => {
            return cur.choiceContent === ''
          })
          if (empty) {
            wx.showModal({
              title: '题目或选项不能为空',
              showCancel: !1
            })
            ready = false
          }
          if (ready) {
            this.$store.dispatch('addProblem', addProblem)
            this.NumberOfOptions = null
            this.selectGroup = []
          }
        }
      },
      onClick1 () { // 选择单选题还是多选题的函数
        $wuxSelect('#wux-select1').open({
          value: this.value1,
          options: [
            '单选题',
            '多选题'
          ],
          onConfirm: (value, index, options) => {
            if (index !== -1) {
              this.value1 = value
              this.teaQuesType = options[index]
              // selectType=this.value1
              this.selectType = this.value1
              this.extra = this.selectType
            }
          }
        })
      },
      correctOptionsConfirm (e) { /// 选题提类型录入和输入的答案个数的校验，单选对单个答案
        this.answers = e.mp.detail.value
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
    margin-left: 25%;
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
