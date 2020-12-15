import Vue from 'vue'
import VueResource from 'vue-resource'
import store from 'store/store'
import "@babel/polyfill"
import App from 'page/App.vue'
import Vuetify from "vuetify"
import router from "./router"

Vue.use(VueResource)
Vue.use(Vuetify)

new Vue({
    router,
    render: a => a(App),
    store,
    vuetify: new Vuetify(),
}).$mount('#app');