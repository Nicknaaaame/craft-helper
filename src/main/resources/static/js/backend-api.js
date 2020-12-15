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
    getItemById(id) {
        return AXIOS.get('/' + id)
    },
    saveItem(item) {
        let data = new FormData()
        if (item.id !== null)
            data.append("id", item.id)
        data.append("name", item.name)
        if (item.icon !== null)
            data.append("icon", item.icon)
        data.append("craftRecipe", JSON.stringify(item.craftRecipe))
        if (item.id !== null)
            return AXIOS.put('/', data);
        return AXIOS.post('/', data);
    },
    saveItemBy(id, name, icon, recipe) {
        let data = new FormData()
        if (id !== null)
            data.append("id", id)
        data.append("name", name)
        if (icon !== null)
            data.append("icon", icon)
        data.append("craftRecipe", JSON.stringify(recipe))
        if (id !== null)
            return AXIOS.put('/', data);
        return AXIOS.post('/', data);
        /*{headers:{"Content-type":"multipart/form-data"}}*/
    },
    deleteItem(id) {
        return AXIOS.delete('/' + id)
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
    sendIcon(icon) {
        let data = new FormData()
        data.append("icon", icon)
        return AXIOS.post('/', data)
    }
}



