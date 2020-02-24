<template>
  <div>
    <div>
      <wux-date-picker :visible="timeVisible" controlled mode="datetime" :value="timeValue" :lang="lang" data-index="1" data-mode="datetime" @confirm="onConfirm" @visibleChange="onVisibleChange" :minDate="minDate"/>
    </div>
    <div>
      <van-nav-bar title="题库" left-text="添加" @clickLeft="addQuestion">
        <van-icon name="search" slot="right" @click="searchSomething"/>
      </van-nav-bar>
    </div>
    <div>
      <van-search :value='value' placeholder='请输入搜索关键词' use-action-slot @change='onSearchChange'>
        <view slot='action' @tap='onClickSearch'>搜索</view>
      </van-search>
    </div>
    <div>
      <wux-filterbar :items="itemses" @change="itemChange"/>
      <div v-if="showTeaQuestionBank" v-for="(aTeaQuestion, aTeaQuestionIndex) in teaMiniBank" :key="aTeaQuestionIndex">
        <question-abbreviation :show-select="showSelect" :a-question="aTeaQuestion"></question-abbreviation>
      </div>
      <div v-if="!showTeaQuestionBank" v-for="(aPubQuestion, aPubQuestionIndex) in pubMiniBank" :key="aPubQuestionIndex">
        <question-abbreviation-no-delete :show-select="showSelect" :a-question="aPubQuestion"></question-abbreviation-no-delete>
      </div>
      <wux-fab-button v-if="showSelect" controlled :position="types[typeIndex]" :theme="colors[colorIndex]" :direction="dirs[dirIndex]" :reverse="reverse" :spaceBetween="spaceBetween" :sAngle="sAngle" :eAngle="eAngle" :buttons="buttons" @change="onChange"/>
    </div>
    <i-message id="message"></i-message>
    <wux-dialog id="wux-dialog" />
    <wux-dialog id="wux-dialog--alert" />
  </div>
</template>
<script>
import QuestionAbbreviation from '../../components/QuestionAbbreviation'
import QuestionAbbreviationNoDelete from '../../components/QuestionAbbreviationNoDelete'
import {mapState, mapGetters} from 'vuex'
import {$Message} from '../../../static/iview/base/index'
import {$wuxDialog} from '../../../static/wux/index'
import dateFormate from '../../utils/dateFormate'
export default {
  data () {
    return {
      teaMiniBank: [],
      pubMiniBank: [],
      minDate: null,
      showTeaQuestionBank: true,
      value: '',
      deadLineSecond: null,
      showSelect: true,
      showSearch: false,
      timeVisible: false,
      timeValue: '',
      lang: 'zh_CN',
      itemses: [{
        type: 'radio',
        label: '我的题库',
        value: 'teaQuestion',
        checked: true,
        children: [{
          label: '全部',
          value: 'all',
          checked: true
        },
        {
          label: '单选题',
          value: 'teaSingleChoice'
        },
        {
          label: '多选题',
          value: 'teaMultipleChoice'
        },
        {
          label: '判断题',
          value: 'teaJudge'
        },
        {
          label: '填空题',
          value: 'teaFillBlank'
        }
        ],
        groups: ['my']
      },
      {
        type: 'radio',
        label: '系统题库',
        value: 'pubQuestion',
        children: [{
          label: '全部',
          value: 'all',
          checked: true
        },
        {
          label: '单选题',
          value: 'pubSingleChoice'
        },
        {
          label: '多选题',
          value: 'pubMultipleChoice'
        },
        {
          label: '判断题',
          value: 'pubJudge'
        },
        {
          label: '填空题',
          value: 'pubFillBlank'
        }
        ],
        groups: ['sys']
      }
      ],
      types: ['topLeft', 'topRight', 'bottomLeft', 'bottomRight', 'center'],
      typeIndex: 3,
      colors: ['light', 'stable', 'positive', 'calm', 'balanced', 'energized', 'assertive', 'royal', 'dark'],
      colorIndex: 3,
      dirs: ['horizontal', 'vertical', 'circle'],
      dirIndex: 0,
      sAngle: 0,
      eAngle: 360,
      spaceBetween: 10
    }
  },
  methods: {
    itemChange (e) {
      let isTeaQuestionBank = e.mp.detail.checkedItems[0].value
      if (isTeaQuestionBank === 'teaQuestion') {
        this.showTeaQuestionBank = true
      } else {
        this.showTeaQuestionBank = false
      }
      let kindOfTeaMiniBank = e.mp.detail.checkedValues[0]
      let kindOfPubMiniBank = e.mp.detail.checkedValues[1]
      switch (kindOfTeaMiniBank) {
        case 'all':
          this.teaMiniBank = this.teaQuestion
          break
        case 'teaSingleChoice':
          this.teaMiniBank = this.teaSingleChoice
          break
        case 'teaMultipleChoice':
          this.teaMiniBank = this.teaMultipleChoice
          break
        case 'teaJudge':
          this.teaMiniBank = this.teaJudge
          break
        case 'teaFillBlank':
          this.teaMiniBank = this.teaFillBlank
          break
        case 'teaWrite':
          this.teaMiniBank = this.teaWrite
          break
      }
      switch (kindOfPubMiniBank) {
        case 'all':
          this.pubMiniBank = this.pubQuestion
          break
        case 'pubSingleChoice':
          this.pubMiniBank = this.pubSingleChoice
          break
        case 'pubMultipleChoice':
          this.pubMiniBank = this.pubMultipleChoice
          break
        case 'pubJudge':
          this.pubMiniBank = this.pubJudge
          break
        case 'pubFillBlank':
          this.pubMiniBank = this.pubFillBlank
          break
        case 'pubWrite':
          this.pubMiniBank = this.pubWrite
          break
      }
    },
    onSearchChange (e) {
    },
    onVisibleChange (e) {
      this.timeVisible = e.mp.detail.visible
    },
    onConfirm (e) {
      $wuxDialog().prompt({
        resetOnClose: true,
        title: '提示',
        content: '确定发布已经选中的题吗',
        fieldtype: 'text',
        password: false,
        defaultText: '',
        placeholder: '请输入作业名称',
        maxlength: 8,
        onConfirm: (e, response) => {
          const homeworkName = response
          const teaId = this.getClassroom.teaId
          const classId = this.getClassroom.classId
          const homeworkContentVOs = this.homeworkContentVOs
          const date = (this.deadLineSecond - new Date().getTime()) / 1000
          const second = date
          const completeHomeworkContent = {second, teaId, classId, homeworkName, homeworkContentVOs}
          this.$store.dispatch('releaseHomework', {completeHomeworkContent, teaId, classId})
        }
      })
      this.deadLineSecond = e.mp.detail.date
    },
    onClickSearch () {
      console.log('搜索')
      this.showSearch = false
    },
    searchSomething () {
      this.showSearch = !this.showSearch
    },
    addQuestion () {
      wx.navigateTo({
        url: '/pages/addProblem/main'
      })
    },
    onChange (e) { /// 实现发布作业功能的函数，待完成，悬浮按钮
      this.minDate = dateFormate.dateFormat('YYYY-mm-dd HH:MM:SS', new Date())
      $Message({
        content: '请选择截止日期',
        type: 'warning'
      })
      this.timeVisible = true
    }
  },
  components: {
    QuestionAbbreviation,
    QuestionAbbreviationNoDelete
  },
  beforeMount () {
    if (this.$mp.query.showSelect === 'true') {
      this.showSelect = true
    } else {
      this.showSelect = false
    }
    this.teaMiniBank = this.teaQuestion
    this.pubMiniBank = this.pubQuestion
    console.log(this.teaMiniBank)
  },
  beforeDestroy () {
    this.$store.dispatch('clearHomeworkContent')
  },
  computed: {
    ...mapState(['teaQuestion', 'teaSingleChoice', 'teaMultipleChoice', 'teaJudge', 'teaFillBlank', 'teaWrite',
      'pubQuestion', 'pubSingleChoice', 'pubMultipleChoice', 'pubJudge', 'pubFillBlank', 'pubWrite', 'homeworkContentVOs', 'classroom', 'addQuestions']),
    ...mapGetters(['getClassroom'])
  }
}
</script>
<style scope>
  .wux-ellipsis {
    width: auto;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    word-wrap: normal
  }
</style>
