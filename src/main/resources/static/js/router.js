import Vue from 'vue'
import Router from "vue-router"
import ItemForm from "component/item/ItemForm.vue"
import Home from "page/Home"
import ItemPage from "./page/OpenItemPage";
import AddItemPage from "./page/AddItemPage";
import EditItemPage from "./page/EditItemPage";

Vue.use(Router)

const routers = [
    {path: '/', component: Home},
    {path: '/additem', component: AddItemPage},
    {path: '/item/:id', component: ItemPage},
    {path: '/edititem/:id', component: EditItemPage},
    {path: '*', redirect: '/'}
]

const router = new Router({
    // mode: 'history',
    routes: routers
});

export default router;