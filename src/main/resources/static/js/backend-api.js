import axios from 'axios'
import itemUtil from "util/item"

const AXIOS = axios.create({
    baseURL: `/api/item`,
    timeout: 1000
});


export default {
    getAllItems() {
        return AXIOS.get(`/`)
    },
    getItemsWithName(name) {
        const params = {
            "name": name
        }
        return AXIOS.get(`/`, {params})
    },
    getRecipeById(id) {
        return AXIOS.get('/recipe/' + id)
    },
    addItem(name, icon, recipe) {
        return AXIOS.post('/', itemUtil.getItemWith(name, icon, recipe));
    },
}


