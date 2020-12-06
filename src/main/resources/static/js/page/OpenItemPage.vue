<template>
    <v-container>
        <recipe-list v-if="item" :item="item"></recipe-list>
    </v-container>
</template>
<script>
    import ItemList from "../component/item/ItemList";
    import api from "backend-api"
    import RecipeList from "../recipe/RecipeList";

    export default {
        components: {RecipeList, ItemList},
        data() {
            return {
                item: null,
                id: this.$route.params.id
            }
        },
        async beforeMount() {
            this.item = await api.getItemById(this.id).then(response => response.data)
        }
    }
</script>