import Vue from 'vue'
import EditPersonalInfo from './EditPersonalInfo'
Vue.config.productionTip = true
App.mpType = 'app'

const editPersonalInfo = new Vue(EditPersonalInfo)
editPersonalInfo.$mount()
