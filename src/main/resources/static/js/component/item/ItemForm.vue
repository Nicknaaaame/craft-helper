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
                                    :rules="rules.name"
                            ></v-text-field>
                            <v-file-input
                                    v-model="icon"
                                    accept="image/*"
                                    label="Icon input"
                            ></v-file-input>
                            <v-btn @click="submit">submit</v-btn>
                            <v-alert :type="alert.type" v-model="alert.value" dismissible class="mt-2">{{alert.text}}</v-alert>
                            <v-card v-for="entry in recipe" :key="entry.item.id">
                                <item-row :item="entry.item">
                                    <template v-slot:content>
                                        <v-card-text class="d-inline-flex">
                                            Amount: {{entry.amount}}
                                            <v-btn :disabled="entry.amount>98" @click="entry.amount++">+</v-btn>
                                            <v-btn :disabled="entry.amount===1" @click="entry.amount--">-</v-btn>
                                            <v-layout justify-end>
                                                <v-btn @click="removeItem(entry.item)">X</v-btn>
                                            </v-layout>
                                        </v-card-text>
                                    </template>
                                </item-row>
                            </v-card>
                        </v-container>
                    </v-card>
                </v-col>
                <v-col>
                    <v-container>
                        <v-card v-for="item in items" :key="item.id" class="my-2">
                            <item-row :item="item">
                                <template v-slot:actions>
                                    <v-btn @click="addItem({item:item, amount:1})">add</v-btn>
                                </template>
                            </item-row>
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
    import ItemRow from "./ItemRow";
    import item from "util/item";

    export default {
        components: {ItemRow, ItemList},
        props: ["items", "item"],
        data() {
            return {
                id: null,
                name: "",
                icon: null,
                recipe: [],
                iconBase64: null,
                rules: {
                    name: [val => (val || '').length > 0 || 'This field is required'],
                },
                alert:{
                    value: false,
                    type: "error",
                    text: "KEK"
                }
            }
        },
        methods: {
            submit() {
                let resultRecipe = []
                this.recipe.forEach(entry => {
                    resultRecipe.push(itemUtil.getRecipeEntryWith(entry.item.id, entry.amount))
                })
                api.saveItem(this.id, this.name, this.icon, resultRecipe).then(response=>{
                    this.alert.type = "success"
                    this.alert.text = "Success"
                    this.alert.value = true;
                    window.setInterval(() => {
                        this.alert.value = false;
                    }, 2000)
                }).catch(err=>{
                    this.alert.type = "error"
                    this.alert.text = "Error"
                    this.alert.value = true;
                    /*window.setInterval(() => {
                        this.alert.value = false;
                    }, 2000)*/
                })
                // window.location.reload()
            },
            addItem(entry) {
                let index = this.items.findIndex(el => el.id === entry.item.id)
                console.log(index)
                this.items.splice(index, 1)
                this.recipe.push({item: entry.item, amount: entry.amount})
            },
            removeItem(_item) {
                let index = this.recipe.findIndex(el => el.item.id === _item.id)
                this.recipe.splice(index, 1)
                this.items.push(_item)
            },
        },
        mounted() {
            if (this.item) {
                this.id = this.item.id
                this.name = this.item.name
                // this.icon = this.item.icon
                if (this.item.icon) {
                    this.iconBase64 = 'data:image/png;base64, ' + this.item.icon
                    fetch(this.iconBase64)
                        .then(res => res.blob())
                        .then(blob => {
                            this.icon = new File([blob], "icon", {type: "image/png"});
                            console.log(this.icon)
                        })
                }
                // let tmp = this.icon
                // this.icon = new Image()
                // this.icon.src = 'data:image/png;base64, ' + tmp
                this.item.craftRecipe.forEach(entry => this.addItem(entry))
                this.items.splice(this.items.findIndex(x => x.id === this.id), 1)
            }
        }
    }
</script>

<style>

</style>