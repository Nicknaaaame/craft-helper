<template>
    <v-container>
        <div v-if="recipe.length===0">
            <h1>This item has no craft recipe</h1>
        </div>
        <div v-else>
            <h1>Here's craft recipe</h1>
            <div>
                <recipe-row v-for="entry in recipe" :entry="entry" :key="entry.item.id"></recipe-row>
            </div>
        </div>
    </v-container>
</template>
<script>
    import ItemRow from "../component/item/ItemRow";
    import RecipeRow from "./RecipeRow";

    export default {
        props: ["recipe"],
        components: {RecipeRow, ItemRow},
    }
</script>