<template>
  <i-tabs :current="current" @change="onTapChange">
    <i-tab key="tab1" title="选择题"></i-tab>
    <i-tab key="tab2" title="填空题"></i-tab>
    <i-tab key="tab3" title="判断题"></i-tab>
  </i-tabs>
</template>

<script>
    export default {
      data () {
        return {
          current: 'tab1'
        }
      },
      methods: {
        onTapChange (event) {
          this.current = event.mp.detail.key
          this.$emit('onTapChange', this.current)
        }
      }
    }
</script>

<style scoped>

</style>
