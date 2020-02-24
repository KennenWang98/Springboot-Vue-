import Vue from 'vue'
import TeacherClassPage from './TeacherClassPage'
Vue.config.productionTip = true
App.mpType = 'app'

const teacherClassPage = new Vue(TeacherClassPage)
teacherClassPage.$mount()
