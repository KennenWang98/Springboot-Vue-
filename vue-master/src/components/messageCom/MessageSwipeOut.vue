<template>
  <div>
    <i-swipeout v-for="item in commentGroup" i-class="i-swipeout-demo-item" :actions="actions" @click="deleteMessage" v-if="(item.see==='true')" :key="commentIndex">
      <view slot="content" style="display: flex;flex-direction: row;">
        <view class="i-swipeout-image" style="background:#FFFFFF;">
          <i-avatar :src="item.url" size="large"></i-avatar>
        </view>
        <view class="i-swipeout-des">
          <view class="i-swipeout-des-h2">{{item.username}}</view>
          <view class="i-swipeout-des-detail">{{item.content}}</view>
        </view>
      </view>
    </i-swipeout>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        // url: '',
        // see: 'true',
        // username: '',
        // content: '',
        actions: [
          {
            name: '删除',
            color: '#fff',
            fontsize: '20',
            width: 100,
            icon: 'delete',
            background: '#ed3f14'
          }
        ]
      }
    },
    props: ['commentGroup'],
    methods: {
      deleteMessage () {
        // this.username = ''
        // this.url = ''
        // this.content = ''
        // this.see = 'false'
        // this.$emit('show', this.see)
        console.log(this.commentIndex)
      }
    }
  }
</script>

<style scoped>
  .cell {
    overflow: hidden;
    display: flex;
    width: 100%;
    height: 75px;
  }
  .cell-info {
    width: 100%;
  }
  .cell-read {
    width: 80px;
    background-color: #ccc;
  }
  .cell-delete {
    width: 40px;
    background-color: red;
  }
  .cell-info {
    width: 100%;
    flex-shrink: 0;
  }
</style>
