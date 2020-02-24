<template>
  <div>
    <wux-wing-blank size="default">
      <wux-card :title="homework.question.quesContent" :extra="quesType" >
        <div slot="body">
          <i-radio-group :current="current" @change="handleChange">
            <i-radio v-for="(radio, index) in radios" position="right" :key="radio.id" :value="radio.name">
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
      current: '',
      objects: [],
      radios: [{
        id: 0,
        name: '对'
      }, {
        id: 1,
        name: '错'
      }]
    }
  },
  methods: {
    handleChange (e) {
      this.current = e.mp.detail.value
      let stuAnswer = 0
      if (this.current === '对') {
        stuAnswer = 1
      }
      this.objects = []
      this.objects.push({stuAnswer})
      const hcId = this.hcId
      const quesType = this.quesType
      const objects = this.objects
      this.$store.dispatch('setStuAnswer', {hcId, quesType, objects})
    }
  },
  beforeMount () {
    this.hcId = this.homework.hcId
    this.quesType = '判断题'
    this.current = ''
    this.objects = []
    if (this.stuAnswer.length > 0) {
      this.stuAnswer.forEach((cur) => {
        if (cur.hcId === this.homework.hcId) {
          const index = cur.objects[0].stuAnswer
          if (index === 1) {
            this.current = '对'
          } else {
            this.current = '错'
          }
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
        this.current = ''
        this.objects = []
        this.hcId = value.hcId
        this.quesType = value.quesType
        if (this.stuAnswer.length > 0) {
          this.stuAnswer.forEach((cur) => {
            if (cur.hcId === value.hcId) {
              const index = cur.objects[0].stuAnswer
              if (index === 1) {
                this.current = '对'
              } else {
                this.current = '错'
              }
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
