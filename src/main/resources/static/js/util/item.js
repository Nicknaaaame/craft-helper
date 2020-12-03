export default {
    getItemWith(name, icon, recipe) {
        return {
            "id": null,
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