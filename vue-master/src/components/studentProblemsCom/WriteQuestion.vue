<template>
  <div>
    <wux-wing-blank size="default">
      <wux-card :title="homework.question.quesContent" :extra="homework.quesType">
        <div slot="body">
          <wux-cell hover-class="none">
            <wux-textarea hasCount clear="true"  rows="3" cursorSpacing="80" placeholder="内容..." :value="current" @change="onChange" @clear="onClear" controlled/>
          </wux-cell>
        </div>
      </wux-card>
    </wux-wing-blank>
  </div>
</template>
<script>
import {mapState} from 'vuex'
export default {
  props: {
    homework: Object
  },
  data () {
    return {
      current: '',
      hcId: '',
      quesType: '',
      objects: []
    }
  },
  methods: {
    onChange (e) {
      this.value = e.mp.detail.value.trim()
      this.objects = []
      const answerEssay = this.value
      this.objects.push({answerEssay})
      const hcId = this.hcId
      const quesType = this.quesType
      const objects = this.objects
      const stuAnswer = {hcId, quesType, objects}
      this.$store.dispatch('setStuAnswer', stuAnswer)
    },
    onClear (e) {
      this.current = ''
    }
  },
  beforeMount () {
    this.hcId = this.homework.hcId
    this.quesType = '作文题'
  },
  computed: {
    ...mapState(['stuAnswer'])
  },
  watch: {
    homework: {
      deep: true,
      handler (value) {
        this.current = ''
        this.objects = []
        this.hcId = value.hcId
        this.quesType = value.quesType
        if (this.stuAnswer.length > 0) {
          this.stuAnswer.forEach((cur) => {
            if (cur.hcId === value.hcId) {
              const current = cur.objects[0].answerEssay
              this.current = current
              this.objects = cur.objects
            }
          })
        }
      }
    }
  }
}
</script>
<style scope>
</style>
