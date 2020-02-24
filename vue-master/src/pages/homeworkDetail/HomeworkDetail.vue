<template>
  <div>
    <wux-navbar theme="dark"><view>题目</view></wux-navbar>
    <problem-view-com :homework="homework" :disabled="disabled"></problem-view-com>
    <stu-pagination @onPageChange="onPageChange" :total="total" :page-current="pageCurrent"></stu-pagination>
  </div>
</template>
<script>
  import StuTap from '../../components/StuTap'
  import StuPagination from '../../components/StuPagination'
  import ProblemViewCom from '../../components/problemsCom/ProblemViewCom'
  import {mapState} from 'vuex'
  export default {
    components: {
      StuTap,
      StuPagination,
      ProblemViewCom
    },
    data () {
      return {
        homework: {},
        total: null,
        disabled: false,
        pageCurrent: 1
      }
    },
    methods: {
      onPageChange (pageCurrent) {
        this.homework = this.homeworkDetail[pageCurrent - 1]
        this.pageCurrent = pageCurrent
      }
    },
    beforeMount () {
      this.homework = this.homeworkDetail[0]
      this.total = this.homeworkDetail.length
      this.disabled = this.$mp.query.disabled
      this.pageCurrent = 1
      console.log('beforeMount')
    },
    computed: {
      ...mapState(['homeworkDetail'])
    }
  }
</script>
<style scoped>
</style>
