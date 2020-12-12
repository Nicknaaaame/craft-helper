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
    addItem(name, icon, recipe) {
        let data = new FormData()
        data.append("name", name)
        if (icon !== null)
            data.append("icon", icon)

        data.append("craftRecipe", JSON.stringify(recipe))
        console.log({"recipe": recipe});
        console.log({"recipe": JSON.stringify(recipe)});
        return AXIOS.post('/', data);
        // console.log(itemUtil.getItemWith(name, icon, recipe))
        // return AXIOS.post('/', itemUtil.getItemWith(name, icon, recipe));
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



