<template>
    <v-container>
        <item-form v-if="item" :items="items" :item="item"></item-form>
    </v-container>
</template>

<script>
    import ItemForm from "../component/item/ItemForm";
    import api from "../backend-api";

    export default {
        components: {ItemForm},
        data() {
            return {
                item: null,
                items: null
            }
        },
        async mounted() {
            api.getAllItems().then(response => this.items = response.data)
            let id = this.$route.params.id
            this.item = await api.getItemById(id).then(response => response.data)
        }
    }
</script>