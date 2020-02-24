<template>
  <div>
    <student-school-list v-if="(currentTapComponent===0)"></student-school-list>
    <message-com v-if="(currentTapComponent===1)"></message-com>
    <my-intercalate v-if="(currentTapComponent===2)"></my-intercalate>
    <three-tab-bar @onChange="changeCurrentTapComponent"></three-tab-bar>
  </div>
</template>
<script>
  import ThreeTabBar from '../ThreeTabBar'
  import StudentSchoolList from '../student/StudentSchoolList'
  import MessageCom from '../messageCom/MessageCom'
  import MyIntercalate from '../myIntercalate.vue/MyIntercalate'
  export default {
    components: {
      ThreeTabBar,
      StudentSchoolList,
      MessageCom,
      MyIntercalate
    },
    data () {
      return {
        currentTapComponent: 0
      }
    },
    methods: {
      changeCurrentTapComponent (value) {
        this.currentTapComponent = value
      }
    }
  }
</script>
<style scope>
</style>

