<template>
  <div>
    <van-cell-group>
      <div v-for="(student,index) in studentRank" :key="index">
        <van-cell :title="student.student.stuName" :value="'排名：'+(index+1)" :label="'平均分：'+student.pointSum" border="true" :icon="student.student.stuIcon"></van-cell>
      </div>
    </van-cell-group>
  </div>
</template>
<script>
import {mapState} from 'vuex'
export default {
  data () {
    return {
      numbers: [1, 2, 3, 4]
    }
  },
  computed: {
    ...mapState(['studentRank'])
  }
}
</script>
<style scope>
</style>
