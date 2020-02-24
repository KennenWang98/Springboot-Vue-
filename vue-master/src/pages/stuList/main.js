import Vue from 'vue'
import StuList from './StuList'
Vue.config.productionTip = true
App.mpType = 'app'

const stuList = new Vue(StuList)
stuList.$mount()
