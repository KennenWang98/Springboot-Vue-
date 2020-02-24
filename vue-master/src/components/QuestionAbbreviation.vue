<template>
  <div>

    <div>
      <wux-card :title="aQuestion.quesType" :actions="actions" @action="goToProDetail">
        <div slot="body" v-if="!showQuestionDetail">
          <view class="wux-ellipsis">
            {{aQuestion.question.quesContent}}
            <wux-selectable v-if="showSelect" color="positive" controlled :checked="aQuestion.checked" @change.prevent="onCheckboxChange" slot="footer" />
          </view>
        </div>
        <div v-else slot="body">
          <problem-view-com :homework="aQuestion" :disabled="disabled"></problem-view-com>
          <wux-selectable v-if="showSelect" color="positive" controlled :checked="aQuestion.checked" @change.prevent="onCheckboxChange" slot="footer" />
        </div>
      </wux-card>
    </div>
    <i-message id="message"></i-message>
    <wux-dialog id="wux-dialog" />
    <wux-dialog id="wux-dialog--alert" />
  </div>
</template>
<script>
  import {$wuxDialog} from '../../static/wux/index'
  import ProblemViewCom from './problemsCom/ProblemViewCom'
  export default {
    props: {
      aQuestion: Object,
      showSelect: Boolean
    },
    components: {
      ProblemViewCom
    },
    data () {
      return {
        toggle2: false,
        actionsSwipeout: [
          {
            name: '删除',
            color: '#fff',
            fontsize: '20',
            width: 100,
            background: '#ed3f14'
          },
          {
            name: '返回',
            width: 100,
            color: '#80848f',
            fontsize: '20',
            icon: 'undo'
          }
        ],
        disabled: true,
        showQuestionDetail: false,
        actions: [{
          type: 'primary',
          text: '删除'
        }, {
          type: 'default',
          text: '查看'
        }]
      }
    },
    methods: {
      onCheckboxChange (e) {
        const flag = this.aQuestion.flag
        const quesId = this.aQuestion.quesAllId
        const homeworkContent = {flag, quesId}
        if (e.mp.detail.checked) {
          this.aQuestion.checked = true
          this.$store.dispatch('addHomeworkContent', homeworkContent)
        } else {
          this.aQuestion.checked = false
          this.$store.dispatch('deleteHomeworkContent', homeworkContent)
        }
        this.$store.dispatch('changeQuestionChecked', this.aQuestion)
      },
      goToProDetail (e) { /// 去往题目详情页面，点击“查看”详情
        if (e.mp.detail.index === 0) {
          $wuxDialog().confirm({
            resetOnClose: true,
            closable: true,
            title: '删除题目',
            content: '你确定要将该题从您的题库中删除吗？',
            onConfirm: () => {
              const quesType = this.aQuestion.quesType
              const quesAllId = this.aQuestion.quesAllId
              this.$store.dispatch('deleteTeaQuestion', {quesType, quesAllId})
            },
            onCancel (e) {
              this.toggle2 = !this.toggle2
            }
          })
        } else {
          this.showQuestionDetail = !this.showQuestionDetail
          if (this.showQuestionDetail) {
            this.actions[1].text = '返回'
          } else {
            this.actions[1].text = '查看'
          }
        }
      }
    }
  }
</script>
<style scope>
  .wux-ellipsis {
    width: auto;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    word-wrap: normal
  }
  .wux-text--right {
    text-align: right !important
  }
</style>
