<template>
    <v-app>
        <v-container>
            <finder-item-list :items="getItems">
                <template v-slot:actions="props">
                    <v-btn :to="`/item/${props.item.id}`">open</v-btn>
                    <v-btn @click="deleteItemAction(props.item.id)">delete</v-btn>
                    <v-btn :to="`/edititem/${props.item.id}`">edit</v-btn>
                </template>
            </finder-item-list>
        </v-container>
    </v-app>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import FinderItemList from "../component/item/ItemList";

    export default {
        components: {FinderItemList},
        computed: {
            ...mapGetters(['getItems']),
        },
        methods: {
            ...mapActions(['deleteItemAction']),
        }
    }
</script>

<style>

</style>