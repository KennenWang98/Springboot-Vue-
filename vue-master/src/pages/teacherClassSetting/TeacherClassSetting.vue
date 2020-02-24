<template>
  <div>
    <wux-navbar theme="assertive">
      <wux-icon slot="left" type="" size="23"/>
      <view>编辑班级信息</view>
      <wux-icon slot="right" type="ios-checkmark" size="23" @click="clickRight"/>
    </wux-navbar>
    <div class="container">
      <wux-select id="wux-select1" />
      <div class="avaterContainer">
        <wux-col span="4">
          <wux-image wux-class="image" width="100px" height="100px" shape="circle" src="https://picsum.photos/750/750/?random&s=2" loading="Loading" />
        </wux-col>
      </div>
      <div class="inputContainer">
        <wux-cell-group title="请输入班级信息">
          <wux-cell hover-class="none">
            <wux-input clear label="班级名称"  controlled :value="classroom.className" @change="classNameChange"/>
          </wux-cell>
          <wux-cell hover-class="none">
            <wux-input clear label="班级简介"  controlled :value="classroom.classText" @change="classTextChange" />
          </wux-cell>
          <wux-cell hover-class="none">
            <wux-input clear label="班级创建时间" disabled :placeholder="classroom.activeTime"/>
          </wux-cell>
          <wux-cell hover-class="none">
            <wux-input clear label="班级截止时间" disabled :placeholder="classroom.remainTime" />
          </wux-cell>
        </wux-cell-group>
      </div>
    </div>
    <i-message id="message"></i-message>
  </div>
</template>
<script>
  import {mapState} from 'vuex'
  import {$Message} from '../../../static/iview/base/index'
  export default {
    data () {
      return {
        classroom: {},
        title1: '',
        value1: '',
        value2: ''
      }
    },
    methods: {
      clickRight () {
        if (this.classroom.className === '') {
          $Message({
            content: '班级名称不能为空',
            type: 'warning'
          })
        } else {
          const classroom = this.classroom
          const classIndex = this.classIndex
          const schoolIndex = this.schoolIndex
          this.$store.dispatch('updateClass', {classroom, classIndex, schoolIndex})
        }
      },
      classNameChange (e) {
        this.classroom.className = e.mp.detail.value.trim()
      },
      classTextChange (e) {
        this.classroom.classText = e.mp.detail.value.trim()
      }
    },
    beforeMount () {
      this.schoolIndex = this.$mp.query.schoolIndex
      this.classIndex = this.$mp.query.classIndex
      this.classroom = this.schoolList[this.schoolIndex].classList[this.classIndex]
    },
    computed: {
      ...mapState(['schoolList'])
    }
  }
</script>

<style scoped>
  .container{
    display: flex;
    flex-direction: column;
  }
</style>
