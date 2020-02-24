<template>
  <div>
    <div>
      <van-search
        v-if="valueSwitch"
        :value='value'
        placeholder='请输入搜索关键词'
        use-action-slot
        @change='onChange'
        @search='onSearch'
      >
        <view slot='action' @tap='onClickSearch'>搜索</view>
      </van-search>
      <van-cell-group v-else>
        <van-field
          :value="phoneNumber"
          center
          clearable
          label="手机号"
          placeholder="请输入学生手机号"
          border="false"
          use-button-slot
          @change="phoneNumberChange"
        >
          <van-button slot="button" size="small" type="danger" @click="addStudent">添加</van-button>
        </van-field>
      </van-cell-group>
      <i-cell title="添加/搜索">
        <i-switch :value="valueSwitch" size="large" @change="onChangeSwitch" slot="footer">
          <view slot="open">搜索</view>
          <view slot="close">添加</view>
        </i-switch>
      </i-cell>
    </div>
    <div>
      <wux-index :height='height' @change='onChangeIndex'>
        <wux-index-item v-for='(item, indexAlphabet) in alphabet' :key='indexAlphabet' :name='item.initial'>
          <div v-for="(student, indexStudent) in item.studentByAlphabet" :key="indexStudent">
            <i-swipeout  i-class='i-swipeout-demo-item' :actions='actions' :toggle='toggle2' unclosable @change='handlerCloseButton' :id="student.phoneNumber">
              <view slot='content'>
                <i-cell
                  i-class='i-cell-padding'
                  :title='student.stuName'
                  :label='student.phoneNumber'>
                </i-cell>
              </view>
            </i-swipeout>
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
import {$wuxDialog} from '../../../static/wux/index'
import {$Message} from '../../../static/iview/base/index'
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
    // 添加学生
    addStudent () {
      const phoneNumber = this.phoneNumber
      const classId = this.classId
      if (phoneNumber) {
        if (this.studentList.find(item => {
          return item.phoneNumber === phoneNumber
        })) {
          $Message({
            content: '该学生已存在',
            type: 'warning'
          })
        } else {
          this.$store.dispatch('addOneStudent', {classId, phoneNumber})
        }
      } else {
        $Message({
          content: '请输入手机号再添加学生',
          type: 'warning'
        })
      }
    },
    onChangeIndex (e) {
    },
    // 删除学生
    handlerCloseButton (event) {
      const position = event.mp.detail.index
      if (position === 0) {
        $wuxDialog().confirm({
          resetOnClose: true,
          closable: true,
          title: '删除学生',
          content: '你确定要将该学生从班级中删除吗？',
          onConfirm: () => {
            let phoneNumber = event.mp.currentTarget.id
            let classId = this.classId
            this.$store.dispatch('deleteStudent', {classId, phoneNumber})
            this.toggle2 = !this.toggle2
          },
          onCancel (e) {
            this.toggle2 = !this.toggle2
          }
        })
      } else {
        this.toggle2 = !this.toggle2
      }
    },
    onChangeSwitch (e) {
      this.valueSwitch = e.mp.detail.value
    },
    phoneNumberChange (e) {
      this.phoneNumber = e.mp.detail.trim()
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
