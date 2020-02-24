import Vue from 'vue'
import TeacherMicrosphere from './TeacherMicrosphere'
Vue.config.productionTip = true
App.mpType = 'app'

const teacherMicrosphere = new Vue(TeacherMicrosphere)
teacherMicrosphere.$mount()
