<template>
  <div>
    <div>
      <i-swipeout  i-class='i-swipeout-demo-item' :actions='actions' :toggle='toggle2' unclosable @change='handlerCloseButton'>
        <view slot='content'>
          <i-cell
            i-class='i-cell-padding'
            title='张涛'
            label='药水弟'>
          </i-cell>
        </view>
      </i-swipeout>
    </div>
  </div>
</template>
<script>
import {$wuxDialog} from '../../static/wux/index'
export default {
  props: {
    student: Object
  },
  data () {
    return {
      toggle2: false,
      actions: [
        {
          name: '删除',
          color: '#fff',
          fontsize: '20',
          width: 100,
          background: '#ed3f14'
        },
        {
          name: '返回',
          width: 100,
          color: '#80848f',
          fontsize: '20',
          icon: 'undo'
        }
      ]
    }
  },
  methods: {
    onChangeIndex (e) {
      console.log(e)
    },
    handlerCloseButton (event) {
      console.log(event)
      const position = event.mp.detail.index
      console.log(position)
      switch (position) {
        case 0:
          $wuxDialog().confirm({
            resetOnClose: true,
            closable: true,
            title: '删除学生',
            content: '你确定要将该学生从班级中删除吗？',
            onConfirm (e) {
              console.log('删除学生')
              this.toggle2 = !this.toggle2
            },
            onCancel (e) {
              console.log('取消')
              this.toggle2 = !this.toggle2
            }
          })
          break
        case 1:
          this.toggle2 = !this.toggle2
          break
      }
    }
  }
}
</script>
<style scope>
</style>
