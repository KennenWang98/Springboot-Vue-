<template>
  <div>
    <wux-wing-blank size="default">
      <wux-card :title="homework.question.quesContent" :extra="homework.quesType" :actions="actions" @action="onAction">
        <div slot="body">
          <wux-cell-group>
            <wux-cell hover-class="none">
              <div v-for="(solution, index) in homework.solutions" :key="index">
                <wux-input :label="index+1" placeholder="请输入该题号对应的答案" controlled :value="solution.solutionContent" @confirm="onConfirm" :disabled="disabled" />
              </div>
            </wux-cell>
          </wux-cell-group>
        </div>
        <div slot="footer">
          <wux-media title="解析" :label="homework.question.solutionText" align="baseline"></wux-media>
        </div>
      </wux-card>
    </wux-wing-blank>
  </div>
</template>
<script>
  export default {
    props: {
      homework: Object,
      disabled: Boolean
    },
    data () {
      return {
        controlled: true,
        actions: [{
          type: 'default',
          text: '查看答案'
        }]
      }
    },
    methods: {
      onAction (e) {
        console.log('onAction', e.mp.detail)
      },
      onConfirm (e) {
        console.log('onConfirm', e)
        this.answer = e.mp.detail.value
      }
    },
    beforeMount () {
      if (this.disabled) {
        this.actions = []
      }
    }
  }
</script>
<style scope>
</style>
