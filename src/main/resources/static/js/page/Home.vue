<template>
    <v-app>
        <v-container>
            <v-text-field
                    label="Find item"
                    v-model="searchName"
                    @change="findItem"
            ></v-text-field>
            <!--TODO: change it to founded items namely sync getItems and foundedItems-->
            <item-list :items="getItems"></item-list>
        </v-container>
    </v-app>
</template>

<script>
    import {mapGetters, mapActions} from 'vuex'
    import ItemList from "component/item/ItemList";
    import api from "../backend-api";

    export default {
        components: {ItemList},
        data() {
            return {
                foundedItems: this.getItems,
                searchName: "",
            }
        },
        computed: mapGetters(['getItems']),
        methods: {
            findItem() {
                console.log(this.foundedItems)
                if (this.searchName !== "") {
                    this.foundedItems = []
                    this.getItems.forEach(item => {
                        if (item.name.toLowerCase().includes(this.searchName.toLowerCase()))
                            this.foundedItems.push(item)
                    })
                } else{

                    this.foundedItems = this.getItems
                }
            }
        },
    }
</script>

<style>

</style>