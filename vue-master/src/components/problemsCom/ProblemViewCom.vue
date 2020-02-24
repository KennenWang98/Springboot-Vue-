<template>
  <div>
    <single-choice v-if="homework.quesType==='单选题'" :homework="homework" :disabled="disabled"></single-choice>
    <multiple-choice v-if="homework.quesType==='多选题'" :homework="homework" :disabled="disabled"></multiple-choice>
    <fill-blank-question v-if="homework.quesType==='填空题'" :homework="homework" :disabled="disabled"></fill-blank-question>
    <judge-question v-if="homework.quesType==='判断题'" :homework="homework" :disabled="disabled"></judge-question>
    <write-question v-if="homework.quesType==='作文题'" :homework="homework" :disabled="disabled"></write-question>
  </div>
</template>
<script>
import SingleChoice from './SingleChoice'
import MultipleChoice from './MultipleChoice'
import FillBlankQuestion from './FillBlankQuestion'
import JudgeQuestion from './JudgeQuestion'
import WriteQuestion from './WriteQuestion'
export default {
  props: {
    homework: Object,
    disabled: Boolean
  },
  components: {
    SingleChoice,
    MultipleChoice,
    FillBlankQuestion,
    JudgeQuestion,
    WriteQuestion
  }
}
</script>
<style scope>
</style>
