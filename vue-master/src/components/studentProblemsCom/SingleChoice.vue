<template>
  <div>
    <wux-wing-blank size="default">
      <wux-card :title="homework.question.quesContent" :extra="homework.quesType">
        <div slot="body">
          <i-radio-group :current="current" @change="handleChange" >
            <i-radio v-for="(solution, index) in homework.solutions" position="right" :value="solution.choiceContent" :key="index">
            </i-radio>
          </i-radio-group>
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
      hcId: '',
      quesType: '',
      objects: [],
      current: ''
    }
  },
  methods: {
    handleChange (e) {
      this.current = e.mp.detail.value
      this.homework.solutions.forEach((cur, index) => {
        if (cur.choiceContent === this.current) {
          const stuAnswer = index
          this.objects = []
          this.objects.push({stuAnswer})
        }
      })
      const hcId = this.hcId
      const quesType = this.quesType
      const objects = this.objects
      this.$store.dispatch('setStuAnswer', {hcId, quesType, objects})
    }
  },
  beforeMount () {
    this.hcId = this.homework.hcId
    this.quesType = '单选题'
    this.current = ''
    this.objects = []
    if (this.stuAnswer.length > 0) {
      this.stuAnswer.forEach((cur) => {
        if (cur.hcId === this.homework.hcId) {
          const index = cur.objects[0].stuAnswer
          this.current = this.homework.solutions[index].choiceContent
          this.objects = cur.objects
        }
      })
      console.log(this.current)
    }
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
              const index = cur.objects[0].stuAnswer
              this.current = value.solutions[index].choiceContent
              this.objects = cur.objects
            }
          })
        }
      }
    },
    stuAnswer: {
      deep: true,
      handler (value) {
      }
    }
  }
}
</script>
<style scope>
</style>
