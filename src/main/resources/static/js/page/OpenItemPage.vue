<template>
    <v-container>
        <recipe-list :item="getItemById(id)"/>
    </v-container>
</template>
<script>
    import {mapGetters} from "vuex"
    import RecipeList from "../recipe/RecipeList";

    export default {
        components: {RecipeList},
        computed: mapGetters(['getItemById']),
        data() {
            return {
                item: null,
                id: this.$route.params.id
            }
        },
    }
</script>