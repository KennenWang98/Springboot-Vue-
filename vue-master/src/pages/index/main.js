import Vue from 'vue'
import Index from './Index'
Vue.config.productionTip = true
App.mpType = 'app'

const index = new Vue(Index)
index.$mount()
