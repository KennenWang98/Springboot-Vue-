<template>
  <div>
    <i-notice-bar  v-for="notice in noticeGroup" icon="systemprompt" loop closable :key="index">
      {{notice.content}}
    </i-notice-bar>
  </div>
</template>

<script>
  export default {
    props: ['noticeGroup']
  }
</script>

<style scoped>

</style>
