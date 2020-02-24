import Vue from 'vue'
import TeacherClassSetting from './TeacherClassSetting'
Vue.config.productionTip = true
App.mpType = 'app'

const teacherClassSetting = new Vue(TeacherClassSetting)
teacherClassSetting.$mount()
