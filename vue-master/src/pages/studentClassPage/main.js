import Vue from 'vue'
import StudentClassPage from './StudentClassPage'
Vue.config.productionTip = true
App.mpType = 'app'

const studentClassPage = new Vue(StudentClassPage)
studentClassPage.$mount()
