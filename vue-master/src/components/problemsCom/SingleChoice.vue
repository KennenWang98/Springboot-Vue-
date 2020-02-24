<template>
  <div>
    <wux-wing-blank size="default">
      <wux-card :title="homework.question.quesContent" :extra="homework.quesType" :actions="actions" @action="onAction">
        <div slot="body">
          <i-radio-group :current="current" @change="handleChange">
              <i-radio v-for="(solution, index) in homework.solutions" position="right" :key="index +1" :value="solution.choiceContent" :disabled="disabled">
              </i-radio>
          </i-radio-group>
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
      current: '',
      actions: [{
        type: 'default',
        text: '查看答案'
      }]
    }
  },
  methods: {
    handleChange (e) {
      console.log(e)
      this.current = e.mp.detail.value
    },
    onAction (e) {
      console.log('onAction', e.mp.detail)
    }
  },
  beforeMount () {
    const solutions = this.homework.solutions
    solutions.forEach(cur => {
      if (cur.isTrue === 1) {
        this.current = cur.choiceContent
      }
    })
    if (this.disabled) {
      this.actions = []
    }
  },
  watch: {
    homework: {
      deep: true,
      handler (value) {
        const solutions = value.solutions
        solutions.forEach(cur => {
          if (cur.isTrue === 1) {
            this.current = cur.choiceContent
          }
        })
        if (this.disabled) {
          this.actions = []
        }
      }
    }
  }
}
</script>
<style scope>
</style>
