import Vue from 'vue'
import Router from "vue-router"
import ItemForm from "component/item/ItemForm.vue"
import Home from "page/Home"

Vue.use(Router)

const routers = [
    {path: '/', component: Home},
    {path: '/itemform', component: ItemForm},
    {path: '*', redirect: '/'}
]

const router = new Router({
    // mode: 'history',
    routes: routers
});

export default router;