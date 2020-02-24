<template>
  <div>
    <wux-wing-blank size="default">
      <wux-card :title="homework.question.quesContent" :extra="homework.quesType">
        <div slot="body">
          <i-checkbox-group :current="current" @change="handleChange">
            <i-checkbox v-for="(solution, index) in homework.solutions" position="right" :key="index" :value="solution.choiceContent">
            </i-checkbox>
          </i-checkbox-group>
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
        current: [],
        objects: []
      }
    },
    methods: {
      handleChange (e) {
        this.objects = []
        const index = this.current.indexOf(e.mp.detail.value)
        index === -1 ? this.current.push(e.mp.detail.value) : this.current.splice(index, 1)
        this.homework.solutions.forEach((curHomework, curIndex) => {
          this.current.forEach((cur) => {
            if (curHomework.choiceContent === cur) {
              const stuAnswer = curIndex
              this.objects.push({stuAnswer})
            }
          })
        })
        const hcId = this.hcId
        const quesType = this.quesType
        const objects = this.objects
        const stuAnswer = {hcId, quesType, objects}
        this.$store.dispatch('setStuAnswer', stuAnswer)
      }
    },
    beforeMount () {
      this.hcId = this.homework.hcId
      this.quesType = '多选题'
      this.current = []
      this.objects = []
      if (this.stuAnswer.length > 0) {
        this.stuAnswer.forEach((cur) => {
          if (cur.hcId === this.homework.hcId) {
            cur.objects.forEach(curO => {
              const index = curO.stuAnswer
              this.current.push(this.homework.solutions[index].choiceContent)
            })
            this.objects = cur.objects
          }
        })
      }
    },
    computed: {
      ...mapState(['stuAnswer'])
    },
    watch: {
      homework: {
        deep: true,
        handler (value) {
          this.current = []
          this.objects = []
          this.hcId = value.hcId
          this.quesType = value.quesType
          if (this.stuAnswer.length > 0) {
            this.stuAnswer.forEach((cur) => {
              if (cur.hcId === value.hcId) {
                cur.objects.forEach(curO => {
                  const index = curO.stuAnswer
                  this.current.push(value.solutions[index].choiceContent)
                })
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

