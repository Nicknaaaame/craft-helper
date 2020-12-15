<template>
    <v-app>
        <v-container>
            <item-list :items="getItems"></item-list>
        </v-container>
    </v-app>
</template>

<script>
    import {mapGetters} from 'vuex'
    import ItemList from "component/item/ItemList";

    export default {
        components: {ItemList},
        computed: mapGetters(['getItems']),
    }
</script>

<style>

</style>