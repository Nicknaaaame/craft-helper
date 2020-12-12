<template>
    <v-app class="ma-10">
        <v-form>
            <v-row>
                <v-col>
                    <v-card>
                        <v-container>
                            <v-text-field
                                    v-model="name"
                                    label="Name"
                                    required
                            ></v-text-field>
                            <v-file-input
                                    v-model="icon"
                                    accept="image/*"
                                    label="Icon input"
                            ></v-file-input>
                            <v-btn @click="submit">submit</v-btn>
                            <v-card v-for="entry in recipe" :key="entry.item.id" class="ma-2">
                                <v-card-text>
                                    {{entry.item.name}}
                                    <v-img v-if="entry.item.icon" :src="`data:image/jpg;base64, ${entry.item.icon}`" height="65" width="65"/>
                                </v-card-text>
                                <v-card-text class="d-inline-flex">
                                    <v-img v-if="entry.item.icon" :src="`data:image/jpg;base64, ${entry.item.icon}`" height="65" width="65"/>
                                    Amount: {{entry.amount}}
                                    <v-btn :disabled="entry.amount>98" @click="entry.amount++">+</v-btn>
                                    <v-btn :disabled="entry.amount===1" @click="entry.amount--">-</v-btn>
                                    <v-layout justify-end>
                                        <v-btn @click="removeItem(entry.item)">X</v-btn>
                                    </v-layout>
                                </v-card-text>
                            </v-card>
                        </v-container>
                    </v-card>
                </v-col>
                <v-col>
                    <v-container>
                        <v-card v-for="item in items" :key="item.id" class="my-2">
                            <v-card-text primary-title>
                                <v-img v-if="item.icon" :src="`data:image/jpg;base64, ${item.icon}`" height="65" width="65"/>
                                {{ item.name }}
                            </v-card-text>
                            <v-card-actions>
                                <v-btn @click="addItem(item)">add</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-container>
                </v-col>
            </v-row>
        </v-form>
    </v-app>
</template>

<script>
    import api from "backend-api"
    import ItemList from "./ItemList";
    import itemUtil from "util/item"

    export default {
        components: {ItemList},
        props: ["items"],
        data() {
            return {
                name: "",
                icon: null,
                recipe: [],
            }
        },
        methods: {
            submit() {
                let resultRecipe = []
                this.recipe.forEach(entry => {
                    resultRecipe.push(itemUtil.getRecipeEntryWith(entry.item.id, entry.amount))
                })
                api.addItem(this.name, this.icon, resultRecipe)
                // window.location.reload()
            },
            addItem(_item) {
                let index = this.items.indexOf(_item)
                this.items.splice(index, 1)
                this.recipe.push({item: _item, amount: 1})
            },
            removeItem(_item) {
                let index = this.recipe.indexOf(_item)
                this.recipe.splice(index, 1)
                this.items.push(_item)
            },
        }
    }
</script>

<style>

</style>