<template>
  <div>
    <wux-wing-blank size="default">
      <wux-card :title="homework.question.quesContent" :extra="homework.quesType" :actions="actions" @action="onAction" >
        <div slot="body">
          <i-radio-group :current="current" @change="handleChange">
            <i-radio v-for="(radio, index) in radios" position="right" :key="radio.id" :value="radio.name" :disabled="disabled">
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
      radios: [{
        id: 1,
        name: '对'
      }, {
        id: 2,
        name: '错'
      }],
      actions: [{
        type: 'default',
        text: '查看答案'
      }]
    }
  },
  methods: {
    handleChange (e) {
      this.current = e.mp.detail.value
    },
    onAction (e) {
      console.log('onAction', e.mp.detail)
    }
  },
  beforeMount () {
    if (this.homework.question.isTrue === 0) {
      this.current = this.radios[1].name
    } else {
      this.current = this.radios[0].name
    }
    if (this.disabled) {
      this.actions = []
    }
  },
  watch: {
    homework: {
      deep: true,
      handler (value) {
        if (value.isTrue === 0) {
          this.current = this.radios[1].name
        } else {
          this.current = this.radios[0].name
        }
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
