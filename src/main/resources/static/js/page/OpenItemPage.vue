<template>
    <v-container>
        <recipe-list :item="item"></recipe-list>
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
                item: {},
                id: this.$route.params.id
            }
        },
        mounted() {
            api.getItemById(this.id).then(response => {
                setTimeout(()=>{

                    this.item = response.data
                    console.log(this.item)
                }, 10)
            })
        }
    }
</script>