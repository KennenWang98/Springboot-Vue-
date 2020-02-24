<template>
  <div>
    <div>
      <van-search
        :value='value'
        placeholder='请输入搜索关键词'
        use-action-slot
        @change='onChange'
        @search='onSearch'
      >
        <view slot='action' @tap='onClickSearch'>搜索</view>
      </van-search>
    </div>
    <div>
      <wux-index :height='height' @change='onChangeIndex'>
        <wux-index-item v-for='(item, indexAlphabet) in alphabet' :key='indexAlphabet' :name='item.initial'>
          <div v-for="(student, indexStudent) in item.studentByAlphabet" :key="indexStudent">
              <view slot='content'>
                <i-cell
                  i-class='i-cell-padding'
                  :title='student.stuName'
                >
                </i-cell>
              </view>
          </div>
        </wux-index-item>
      </wux-index >
    </div>
    <i-message id="message" />
    <wux-dialog id="wux-dialog" />
    <wux-dialog id="wux-dialog--alert" />
  </div>
</template>
<script>
import {mapState, mapGetters} from 'vuex'
export default {
  data () {
    return {
      classId: null,
      phoneNumber: '',
      value: '',
      valueSwitch: true,
      height: 500,
      alphabet: [],
      cities: [],
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
    onChange (e) {
      console.log(e, '搜索框中的值发生改变')
    },
    onClickSearch () {
      console.log('点击搜索框')
    },
    onChangeSwitch (e) {
      this.valueSwitch = e.mp.detail.value
    },
    initArray () {
      const alphabet = []
      if (this.studentList.length > 0) {
        'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split('').forEach((initial) => {
          const studentByAlphabet = this.studentList.filter((student) => {
            return student.pinyin.charAt(0) === initial
          })
          if (studentByAlphabet.length > 0) {
            alphabet.push({
              initial,
              studentByAlphabet
            })
          }
        })
      }
      this.alphabet = alphabet
    }
  },
  components: {
  },
  computed: {
    ...mapState(['studentList']),
    ...mapGetters(['allStudent'])
  },
  watch: {
    allStudent (newValue) {
      this.studentList = newValue
      this.initArray()
    }
  },
  beforeMount () {
    /* const alphabet = []
    'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split('').forEach((initial) => {
      const studentByAlphabet = this.studentList.filter((student) => {
        return student.pinyin.charAt(0) === initial
      })
      if (studentByAlphabet.length > 0) {
        alphabet.push({
          initial,
          studentByAlphabet
        })
      }
    })
    this.alphabet = alphabet */
    this.initArray()
    this.classId = this.$mp.query.classId
  }
}
</script>
<style scope>
</style>
