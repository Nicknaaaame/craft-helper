import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'component/App.vue'
import Vuetify from "vuetify"
Vue.use(VueResource)
Vue.use(Vuetify)

new Vue({
    el: "#app",
    render: a => a(App),
    vuetify: new Vuetify(),
})