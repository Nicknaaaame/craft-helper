export default {
    getItemWith(id, name, icon, recipe) {
        return {
            "id": id,
            "name": name,
            "icon": icon,
            "craftRecipe": recipe,
        }
    },
    getRecipeEntryWith(id, amount) {
        return {
            "id": id,
            "amount": amount
        }
    }
}