import Vue from 'vue'
import Identification from './Identification'
Vue.config.productionTip = true
App.mpType = 'app'

const identification = new Vue(Identification)
identification.$mount()
