import Vue from 'vue'
import HomeworkDetail from './HomeworkDetail'
Vue.config.productionTip = true
App.mpType = 'app'

const homeworkDetail = new Vue(HomeworkDetail)
homeworkDetail.$mount()
