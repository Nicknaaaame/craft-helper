<template>
    <v-container>
        <item-row v-for="item in items" :item="item" :key="item.id">
            <template v-slot:actions>
                <v-btn :to="`/item/${item.id}`">open</v-btn>
                <v-btn @click="del(item.id)">delete</v-btn>
                <v-btn :to="`/edititem/${item.id}`">edit</v-btn>
            </template>
        </item-row>
    </v-container>
</template>

<script>
    import ItemRow from "./ItemRow";
    import api from "../../backend-api";

    export default {
        components: {ItemRow},
        props: ['items'],
        methods: {
            del(id) {
                api.deleteItem(id)
            }
        }
    }
</script>