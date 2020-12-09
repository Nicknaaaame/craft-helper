<template>
    <v-expansion-panels multiple>
        <v-expansion-panel v-if="item" v-for="entry in item.craftRecipe" :entry="entry" :key="entry.item.id"
                           :set="resultAmount = entry.amount * amount">
            <v-expansion-panel-header>
                <recipe-row :entry="entry" :resultAmount="resultAmount" :parent-tree="$props" ></recipe-row>
            </v-expansion-panel-header>
            <v-expansion-panel-content>
                <recipe-panels-tree :item="entry.item" :amount="resultAmount"
                                    :parent-tree="$root.$data" :parent-index="this.index++"></recipe-panels-tree>
            </v-expansion-panel-content>
        </v-expansion-panel>
    </v-expansion-panels>
</template>

<script>
    import RecipeRow from "./RecipeRow"

    export default {
        name: "recipePanelsTree",
        components: {RecipeRow},
        props: ['item', 'amount', 'parentTree', 'parentIndex'],
        data() {
            return{
                index:0
            }
        }
    }
</script>