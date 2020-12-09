<template>
    <v-container>
        <div v-if="item.craftRecipe.length===0">
            <h1>This item has no craft recipe</h1>
        </div>
        <div v-else>
            <h1>Here's craft recipe of "{{item.name}}"</h1>
            <div>
                <recipe-panels-tree :item="item" :amount="1"></recipe-panels-tree>
            </div>
        </div>
    </v-container>
</template>
<script>
    import ItemRow from "../component/item/ItemRow";
    import RecipePanelsTree from "./RecipePanelsTree";

    export default {
        props: ["item"],
        components: {RecipePanelsTree, ItemRow},
    }
</script>