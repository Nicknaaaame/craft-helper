<template>
    <v-container>
        <item-form v-if="item" :items="getItems" :item="item"></item-form>
    </v-container>
</template>

<script>
    import ItemForm from "../component/item/ItemForm";
    import api from "../backend-api";
    import {mapGetters} from "vuex"

    export default {
        components: {ItemForm},
        data() {
            return {
                item: null,
            }
        },
        computed: mapGetters(['getItems']),
        async mounted() {
            let id = this.$route.params.id
            this.item = await api.getItemById(id).then(response => response.data)
        }
    }
</script>