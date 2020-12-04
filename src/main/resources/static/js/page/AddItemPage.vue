<template>
    <v-container>
        <item-form :items="items"></item-form>
    </v-container>
</template>

<script>
    import ItemForm from "../component/item/ItemForm";
    import api from "../backend-api";

    export default {
        components: {ItemForm},
        data() {
            return {
                items: null
            }
        },
        mounted() {
            api.getAllItems().then(response => this.items = response.data)
        }
    }
</script>