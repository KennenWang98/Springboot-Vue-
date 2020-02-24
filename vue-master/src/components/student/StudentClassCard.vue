<template>
  <div>
    <div v-for="(classroom, index) in classList" :key="index">
      <i-card v-if="classroom.expired===1" :title="classroom.className" :extra="classroom.teaName" :thumb="classroom.classIcon">
        <view slot="content">班级简介</view>
        <i-cell slot="footer" :title="classroom.classText" is-link :url="'/pages/studentClassPage/main?classIndex='+index+'&schoolIndex='+schoolIndex"></i-cell>
      </i-card>
      <i-card v-else :title="classroom.className" :extra="classroom.teaName" :thumb="classroom.classIcon">
        <view slot="content">班级已过期</view>
        <i-cell slot="footer" :title="classroom.classText" is-link ></i-cell>
      </i-card>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    classList: Array,
    schoolIndex: Number
  },
  data () {
    return {
    }
  }
}
</script>
<style scope>
</style>
