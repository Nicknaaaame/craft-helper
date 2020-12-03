<template>
    <v-app>
        <v-form>
            <v-container>
                <v-text-field
                        v-model="name"
                        :counter="10"
                        label="Name"
                        required
                ></v-text-field>
                <v-file-input
                        v-model="icon"
                        accept="image/*"
                        label="Icon input"
                ></v-file-input>
                <v-card v-for="entry in recipe" :key="entry.item.id">
                    <v-card-text>
                        {{entry.item.name}}
                    </v-card-text>
                    <v-text-field
                            hide-details
                            type="number"
                            v-model="entry.amount"
                    />
                </v-card>
                <v-btn @click="submit">submit</v-btn>
            </v-container>
            <v-container>
                <v-card v-for="item in items" :key="item.id" class="my-2">
                    <v-card-text primary-title>
                        {{ item.icon }}
                        {{ item.name }}
                    </v-card-text>
                    <v-card-actions>
                        <v-btn @click="addItem(item)">add</v-btn>
                    </v-card-actions>
                </v-card>
            </v-container>
        </v-form>
    </v-app>
</template>

<script>
    import api from "backend-api"
    import ItemList from "./ItemList";
    import itemUtil from "util/item"

    export default {
        components: {ItemList},
        data() {
            return {
                name: "",
                icon: null,
                recipe: [],
                items: [],
            }
        },
        methods: {
            submit() {
                let resultRecipe = []
                this.recipe.forEach(entry=>{
                    resultRecipe.push(itemUtil.getRecipeEntryWith(entry.item.id, entry.amount))
                })
                api.addItem(this.name, this.icon, resultRecipe)
            },
            addItem(_item) {
                let index = this.items.indexOf(_item)
                this.items.splice(index, 1)
                this.recipe.push({item: _item, amount: 1})
            }
        },
        mounted() {
            api.getAllItems().then(response => this.items = response.data)
            // api.getAllItems().then(response => {
            //     response.data.forEach(entry=>{
            //         this.items.push({item: entry, amount: 1})
            //     })
            // })
        }
    }
</script>

<style>

</style>