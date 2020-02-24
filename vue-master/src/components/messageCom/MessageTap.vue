<template>
    <div>
      <i-tabs :current="current" @change="handleChange">
        <i-tab key="tab1" title="通知"></i-tab>
        <i-tab key="tab2" title="评论"></i-tab>
        <i-tab key="tab3" title="私信"></i-tab>
      </i-tabs>
    </div>
</template>

<script>
  export default {
    data () {
      return {
        current: 'tab1'
      }
    },
    methods: {
      handleChange (event) {
        this.current = event.mp.detail.key
        this.$emit('handleChange', this.current)
      }
    }
  }
</script>

<style scoped>

</style>
