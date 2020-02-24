<template>
  <div>
    <wux-wing-blank size="default">
      <wux-card :title="homework.question.quesContent" :extra="homework.quesType" @action="onAction">
        <div slot="body">
          <wux-cell hover-class="none">
            <wux-textarea hasCount clear="true"  rows="3" cursorSpacing="80" placeholder="内容..." :value="value" @change="onChange" @clear="onClear" controlled/>
          </wux-cell>
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
    homework: Object
  },
  data () {
    return {
      disabled: false,
      value: 'aaaa'
    }
  },
  methods: {
    onAction (e) {
      console.log('onAction', e.mp.detail)
    },
    onChange (e) {
      this.value = e.mp.detail.value
    },
    onClear (e) {
      this.value = ''
    }
  },
  beforeMount () {
    this.disabled = true
  }
}
</script>
<style scope>
</style>
