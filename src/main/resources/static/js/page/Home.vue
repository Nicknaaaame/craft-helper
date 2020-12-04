<template>
    <v-app>
        <v-container>
            <item-list :items="items"></item-list>
        </v-container>
    </v-app>
</template>

<script>
    import ItemList from "component/item/ItemList";
    import api from "../backend-api";

    export default {
        components: {ItemList},
        data() {
            return {
                items: null,
                foundedItems: null,
                searchName: ""
            }
        },
        mounted() {
            api.getAllItems().then(response => {
                this.items = response.data
            })
        },
    }
</script>

<style>

</style>