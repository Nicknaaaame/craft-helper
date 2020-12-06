<template>
    <v-container>
        <div v-if="item.craftRecipe.length===0">
            <h1>This item has no craft recipe</h1>
        </div>
        <div v-else>
            <h1>Here's craft recipe</h1>
            <div>
                <template>
                    <recipe-row v-for="entry in item.craftRecipe" :entry="entry" :key="entry.item.id"></recipe-row>
                </template>
            </div>
        </div>
    </v-container>
</template>
<script>
    import ItemRow from "../component/item/ItemRow";
    import RecipeRow from "./RecipeRow";

    export default {
        props: ["item"],
        components: {RecipeRow, ItemRow},
    }
</script>