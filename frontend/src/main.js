import Vue from 'vue'
import App from './App.vue'
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'

Vue.prototype.$eventHub = new Vue(); // Global event bus...

Vue.config.productionTip = false

// Bootstrap
Vue.use(BootstrapVue)

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')




