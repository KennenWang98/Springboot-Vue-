<template>
    <div>
        <wux-card :title="aQuestion.quesType" :actions="actions" @action="goToProDetail">
          <view slot="body" class="wux-ellipsis">
            {{aQuestion.question.quesContent}}
            <wux-selectable v-if="showSelect" controlled  color="positive" :checked="aQuestion.checked" @change="onCheckboxChange" slot="footer" />
          </view>
        </wux-card>
      <i-message id="message"></i-message>
      <wux-dialog id="wux-dialog" />
      <wux-dialog id="wux-dialog--alert" />
    </div>
</template>
<script>
export default {
  props: {
    aQuestion: Object,
    showSelect: Boolean
  },
  data () {
    return {
      checked: true,
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
      actions: [{
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
        console.log('查看')
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
