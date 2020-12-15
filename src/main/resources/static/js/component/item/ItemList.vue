<template>
    <v-container>
        <v-text-field
                label="Find item"
                v-model="searchName"
                @change="findItem"
        ></v-text-field>
        <item-row v-for="item in findItem()" :item="item" :key="item.id">
            <template v-slot:actions>
                <v-btn :to="`/item/${item.id}`">open</v-btn>
                <v-btn @click="deleteItemAction(item.id)">delete</v-btn>
                <v-btn :to="`/edititem/${item.id}`">edit</v-btn>
            </template>
        </item-row>
    </v-container>
</template>

<script>
    import {mapActions} from "vuex"
    import ItemRow from "./ItemRow";

    export default {
        components: {ItemRow},
        props: ['items'],
        data() {
            return {
                searchName: "",
            }
        },
        methods: {
            ...mapActions(['deleteItemAction']),
            findItem() {
                let foundedItems = []
                if (this.searchName !== "") {
                    this.items.forEach(item => {
                        if (item.name.toLowerCase().includes(this.searchName.toLowerCase()))
                            foundedItems.push(item)
                    })
                } else{
                    foundedItems = this.items
                }
                return foundedItems
            }
        }
    }
</script>