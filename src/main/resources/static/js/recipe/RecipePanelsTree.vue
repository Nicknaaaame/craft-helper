<template>
    <v-expansion-panels multiple>
        <v-expansion-panel v-if="item" v-for="entry in item.craftRecipe" :entry="entry" :key="entry.item.id">
            <v-expansion-panel-header>{{entry.item.name}}</v-expansion-panel-header>
            <v-expansion-panel-content :set="resultAmount = entry.amount * amount">
                {{resultAmount}}
                <recipe-tree :item="entry.item" :amount="resultAmount"></recipe-tree>
            </v-expansion-panel-content>
        </v-expansion-panel>
    </v-expansion-panels>
</template>

<script>
    export default {
        name: "recipeTree",
        props: ['item', 'amount']
    }
</script>