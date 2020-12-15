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
    updateItem(item) {
        let data = new FormData()
        data.append("id", item.id)
        data.append("name", item.name)
        if (item.icon !== null)
            data.append("icon", item.icon)
        data.append("craftRecipe", JSON.stringify(item.craftRecipe))
        return AXIOS.put('/', data);
    },
    saveItem(item) {
        let data = new FormData()
        data.append("name", item.name)
        if (item.icon !== null)
            data.append("icon", item.icon)
        data.append("craftRecipe", JSON.stringify(item.craftRecipe))
        return AXIOS.post('/', data);
    },
    deleteItem(id) {
        return AXIOS.delete('/' + id)
    },
}



