<template>
    <v-container>
        <recipe-list :recipe="recipe"></recipe-list>
    </v-container>
</template>
<script>
    import ItemList from "../component/item/ItemList";
    import api from "backend-api"
    import RecipeList from "../recipe/RecipeList";
    import item from "../util/item";

    export default {
        components: {RecipeList, ItemList},
        data() {
            return {
                recipe: [],
                id: this.$route.params.id
            }
        },
        mounted() {
            api.getRecipeById(this.id).then(response => {
                // console.log(response.data)
                response.data.forEach(entry => {
                    this.recipe.push({
                        item: entry.item,
                        amount: entry.amount
                    })
                })
                /*console.log(this.recipe)
                this.recipe.forEach(entry=>{
                    console.log(entry.item.name, entry.amount)
                })*/
            })
        }
    }
</script>