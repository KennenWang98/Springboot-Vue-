import Vue from 'vue'
import ManagementClassMembers from './ManagementClassMembers'
Vue.config.productionTip = true
App.mpType = 'app'

const managementClassMembers = new Vue(ManagementClassMembers)
managementClassMembers.$mount()
