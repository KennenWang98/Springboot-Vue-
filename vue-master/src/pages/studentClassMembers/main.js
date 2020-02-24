import Vue from 'vue'
import StudentClassMembers from './StudentClassMembers'
Vue.config.productionTip = true
App.mpType = 'app'

const studentClassMembers = new Vue(StudentClassMembers)
studentClassMembers.$mount()
