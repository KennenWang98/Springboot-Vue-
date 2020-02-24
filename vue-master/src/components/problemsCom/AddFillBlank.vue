<template>
  <div>
    <wux-wing-blank size="default">
      <wux-card title="填空题录入" :extra="extra">
        <div slot="body">
          <wux-cell hover-class="none">
            <wux-textarea rows="4" controlled v-bind:value="quesContent" cursorSpacing="80" placeholder="请输入要录入的填空题题目" type="text" @change="getQues"/>
          </wux-cell>
          <wux-cell hover-class="none" v-for="(blank, index) in blankGroup" :key="index">
            <wux-input :id="index" :label="blank.lable" clear  :placeholder="blank.placeholder" :value="blank.solutionContent" controlled @change="getBlankValue" />
          </wux-cell>
          <wux-cell hover-class="none">
            <wux-textarea controlled rows="4" clear  :value="solutionText" cursorSpacing="80"  placeholder="请输入此题解析" type="text" @change="getComment"/>
          </wux-cell>
          <button class='goodbutton'  hover-class="hover" @click="onProduce">添占位符</button>
          <button class='goodbutton'  hover-class="hover" @click="onRevoke">撤销</button>
          <button class='goodbutton'  hover-class="hover" @click="onReset">重置</button>
          <button class='goodbutton'  hover-class="hover" @click="onSumbmit">提交</button>
        </div>
      </wux-card>
    </wux-wing-blank>
    <i-message id="message"></i-message>
  </div>
</template>

<script>
  import {mapState} from 'vuex'
  let blankNO = 49 /// 题目标号
  export default {
    data () {
      return {
        blankGroup: [], /// 用来动态生成输入框的数组，这个数组有多少元素对象生成多少输入框，学生输入答案存在这个数组的value里面
        quesContent: '', /// 文本框老师输入的题目的题干的值
        NumberOfBlank: '', /// 填空的个数
        solutionText: '' /// 老师解析的值
      }
    },
    methods: {
      onProduce (e) { /// 输入填空个数时动态生成数组,并动态添加特殊符号到题干的函数
        if (blankNO > 54) {
          wx.showModal({
            title: '填空个数不能超过6个哦',
            showCancel: !1
          })
        } else {
          this.quesContent = this.quesContent + '¿'
          this.blankGroup.push({lable: String.fromCharCode(blankNO), placeholder: '请输入第' + String.fromCharCode(blankNO) + '填空答案', solutionContent: ''})
          blankNO++
        }
      },
      getQues (e) {
        this.quesContent = e.mp.detail.value.trim() /// 获取题目
      },
      getComment (e) { /// 获得老师的点评
        this.solutionText = e.mp.detail.value.trim()
      },
      getBlankValue (e) {
        const index = parseInt(e.currentTarget.id)
        this.blankGroup[index].solutionContent = e.mp.detail.value.trim()
      },
      onSumbmit () { /// 获得了用户输入的题干，可以在这里传输题干给后端
        const teaId = this.classroom.teaId
        const classId = this.classroom.classId
        const teaQuesType = '填空题'
        const teacherQuestions = {teaId, classId, teaQuesType}
        const quesContent = this.quesContent
        const solutionText = this.solutionText
        const teaFillQuestion = {quesContent, solutionText}
        const teaFillSolutions = this.blankGroup
        const addProblem = {teacherQuestions, teaFillSolutions, teaFillQuestion}
        let ready = true
        if (quesContent === '') {
          wx.showModal({
            title: '题目或填空答案不能为空',
            showCancel: !1
          })
          ready = false
        }
        const empty = this.blankGroup.find(cur => {
          return cur.solutionContent === ''
        })
        if (empty) {
          wx.showModal({
            title: '题目或填空答案不能为空',
            showCancel: !1
          })
          ready = false
        }
        if (quesContent.split('¿').length - 1 !== teaFillSolutions.length) {
          if (quesContent.charAt(quesContent.length - 1) !== '¿') {
            wx.showModal({
              title: '占位符个数与对应答案个数不符，请重置重新输入',
              showCancel: !1
            })
            ready = false
          }
        }
        if (ready) {
          this.$store.dispatch('addProblem', addProblem)
        }
      },
      onReset () { /// 重置用户输入
        this.quesContent = ''
        this.blankGroup = []
        blankNO = 49
      },
      onRevoke () { /// 撤销上一步操作（输入框减少一个，占位符减少一个）
        this.blankGroup.pop()
        this.quesContent = this.quesContent.substring(0, this.quesContent.lastIndexOf('¿'))
        blankNO--
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
    margin-left: 30%;
    width: 32%;
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
