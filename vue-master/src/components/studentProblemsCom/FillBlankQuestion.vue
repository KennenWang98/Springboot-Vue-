<template>
  <div>
    <wux-wing-blank size="default">
      <wux-card :title="homework.question.quesContent" :extra="homework.quesType">
        <div slot="body">
          <wux-cell-group>
            <wux-cell hover-class="none">
              <div v-for="(solution, index) in homework.solutions" :key="index">
                <wux-input :id="index" :label="index+1" placeholder="请输入该题号对应的答案" controlled :value="objects[index].stuAnswer" @change="onConfirm"/>
              </div>
            </wux-cell>
          </wux-cell-group>
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
        controlled: true,
        hcId: '',
        quesType: '',
        objects: []
      }
    },
    methods: {
      onConfirm (e) {
        const index = parseInt(e.currentTarget.id)
        this.objects[index].stuAnswer = e.mp.detail.value.trim()
        const hcId = this.hcId
        const quesType = this.quesType
        const objects = this.objects
        const stuAnswer = {hcId, quesType, objects}
        this.$store.dispatch('setStuAnswer', stuAnswer)
      }
    },
    beforeMount () {
      this.hcId = this.homework.hcId
      this.quesType = '填空题'
      const stuAnswer = ''
      const answerNo = null
      this.homework.solutions.forEach(() => {
        this.objects.push({stuAnswer, answerNo})
      })
      if (this.stuAnswer.length > 0) {
        this.stuAnswer.forEach((cur) => {
          if (cur.hcId === this.homework.hcId) {
            this.objects = cur.objects
          }
        })
      }
    },
    watch: {
      homework: {
        deep: true,
        handler (value) {
          this.current = []
          this.objects = []
          this.hcId = value.hcId
          this.quesType = value.quesType
          const stuAnswer = ''
          const answerNo = null
          value.solutions.forEach(() => {
            this.objects.push({stuAnswer, answerNo})
          })
          if (this.stuAnswer.length > 0) {
            this.stuAnswer.forEach((cur) => {
              if (cur.hcId === value.hcId) {
                this.objects = cur.objects
              }
            })
          }
        }
      }
    },
    computed: {
      ...mapState(['stuAnswer'])
    }
  }
</script>
<style scope>
</style>
