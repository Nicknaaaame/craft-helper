import Vue from 'vue'
import Router from "vue-router"
import ItemForm from "component/item/ItemForm.vue"
import Home from "page/Home.vue"

Vue.use(Router)

const routers = [
    {path: '/', component: Home},
    {path: '/itemform', component: ItemForm},
    // otherwise redirect to home
    {path: '*', redirect: '/'}
]

const router = new Router({
    mode: 'history',
    routes: routers
});

export default router;