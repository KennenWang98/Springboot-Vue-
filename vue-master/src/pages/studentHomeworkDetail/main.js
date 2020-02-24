import Vue from 'vue'
import StudentHomeworkDetail from './StudentHomeworkDetail'
Vue.config.productionTip = true
App.mpType = 'app'

const studentHomeworkDetail = new Vue(StudentHomeworkDetail)
studentHomeworkDetail.$mount()
