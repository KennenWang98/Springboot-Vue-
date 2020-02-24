<template>
  <div>
    <wux-wing-blank size="default">
      <wux-card :title="homework.question.quesContent" :extra="homework.quesType" :actions="actions" @action="onAction">
        <div slot="body">
          <i-checkbox-group :current="current" @change="handleChange">
            <i-checkbox v-for="(solution, index) in homework.solutions" position="right" :key="index" :value="solution.choiceContent" :disabled="disabled">
            </i-checkbox>
          </i-checkbox-group>
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
        current: [],
        actions: [{
          type: 'default',
          text: '查看答案'
        }]
      }
    },
    methods: {
      handleChange (e) {
        const index = this.current.indexOf(e.mp.detail.value)
        index === -1 ? this.current.push(e.mp.detail.value) : this.current.splice(index, 1)
      },
      onAction (e) {
        console.log('onAction', e.mp.detail)
      }
    },
    beforeMount () {
      const solutions = this.homework.solutions
      solutions.forEach(cur => {
        if (cur.isTrue === 1) {
          this.current.push(cur.choiceContent)
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
          this.current = []
          const solutions = value.solutions
          solutions.forEach(cur => {
            if (cur.isTrue === 1) {
              this.current.push(cur.choiceContent)
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

