<template>
  <div>
    <wux-navbar theme="dark"><view>学生成绩</view></wux-navbar>
    <div v-for="(student, index) in studentRank" :key="index">
      <i-card :title="student.student.stuName" :thumb="student.student.stuIcon">
        <i-cell slot="footer" :title="'排名：'+(index+1)" :label="'总分：'+student.percentage"></i-cell>
      </i-card>
    </div>
  </div>
</template>
<script>
  import {mapState} from 'vuex'
  export default {
    data () {
      return {
      }
    },
    computed: {
      ...mapState(['studentRank'])
    }
  }
</script>
<style scoped>

</style>
