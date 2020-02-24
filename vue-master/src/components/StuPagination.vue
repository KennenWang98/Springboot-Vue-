<template>
  <wux-pagination :current="current" :total="total" @change="onChange" controlled>
    <view slot="prev">上一页</view>
    <view slot="next">下一页</view>
  </wux-pagination>
</template>

<script>
    export default {
      props: {
        total: Number,
        pageCurrent: {
          default: 1
        }
      },
      data () {
        return {
          current: 1
        }
      },
      methods: {
        onChange (e) {
          if (e.mp.detail.current <= this.total) {
            this.current = e.mp.detail.current
            this.$emit('onPageChange', this.current)
          }
        }
      },
      beforeMount () {
        this.current = this.pageCurrent
      },
      watch: {
        pageCurrent: {
          deep: true,
          handler (value) {
            this.current = value
          }
        }
      }
    }
</script>

<style scoped>

</style>
