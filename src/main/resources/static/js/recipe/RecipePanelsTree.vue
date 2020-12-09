<template>
    <v-expansion-panels multiple>
        <v-expansion-panel v-if="item" v-for="entry in item.craftRecipe" :entry="entry" :key="entry.item.id"
                           :set="resultAmount = entry.amount * amount">
            <v-expansion-panel-header>
                <recipe-row :entry="entry" :resultAmount="resultAmount" :parent-amount="amount" :parent-data="$children"/>
            </v-expansion-panel-header>
            <v-expansion-panel-content eager>
                <recipe-panels-tree :item="entry.item" :amount="resultAmount"/>
            </v-expansion-panel-content>
        </v-expansion-panel>
    </v-expansion-panels>
</template>

<script>
    import RecipeRow from "./RecipeRow"

    export default {
        name: "recipePanelsTree",
        components: {RecipeRow},
        props: ['item', 'amount'],
        data() {
            return {
                index: 0,
                currRow:null
            }
        }
    }
</script>