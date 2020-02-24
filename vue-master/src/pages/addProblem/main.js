import Vue from 'vue'
import AddProblem from './AddProblem'
Vue.config.productionTip = true
App.mpType = 'app'

const addProblem = new Vue(AddProblem)
addProblem.$mount()
