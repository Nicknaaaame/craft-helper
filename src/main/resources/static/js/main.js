import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'page/App.vue'
import Vuetify from "vuetify"
import router from "./router"

Vue.use(VueResource)
Vue.use(Vuetify)

new Vue({
    router,
    render: a => a(App),
    vuetify: new Vuetify(),
}).$mount('#app');