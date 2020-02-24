import Vue from 'vue'
import QuestionBank from './QuestionBank'
Vue.config.productionTip = true
App.mpType = 'app'

const questionBank = new Vue(QuestionBank)
questionBank.$mount()
