<template>
    <v-app>
        <v-container>
            <v-text-field
                    label="Find item"
                    v-model="searchName"
                    @change="findItem"
            ></v-text-field>
            <item-list :items="foundedItems"></item-list>
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
                foundedItems:null,
                searchName:""
            }
        },
        methods: {
            findItem() {
                console.log(this.foundedItems)
                if(this.searchName!==""){
                    this.foundedItems = []
                    this.items.forEach(item=>{
                        if(item.name.toLowerCase().includes(this.searchName.toLowerCase()))
                            this.foundedItems.push(item)
                    })
                } else
                    this.foundedItems = this.items
            }
        },
        mounted() {
            api.getAllItems().then(response => {
                this.items = response.data
                this.foundedItems = this.items
            })
        },
    }
</script>

<style>

</style>