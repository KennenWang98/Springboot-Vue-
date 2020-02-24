import Vue from 'vue'
import CreateClass from './CreateClass'
Vue.config.productionTip = true
App.mpType = 'app'

const createClass = new Vue(CreateClass)
createClass.$mount()
