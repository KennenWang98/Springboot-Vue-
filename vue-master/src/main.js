import Vue from 'vue'
import App from './App'
import Fly from 'flyio/dist/npm/wx'
import store from './store/index'

Vue.config.productionTip = true
App.mpType = 'app'
Vue.prototype.$store = store
let fly = new Fly()
Vue.prototype.$fly = fly
const app = new Vue(App)
app.$mount()
