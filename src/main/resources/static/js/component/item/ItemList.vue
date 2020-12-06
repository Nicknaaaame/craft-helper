<template>
    <v-container>
        <item-row v-for="item in items" :item="item" :key="item.id">
        </item-row>
    </v-container>
</template>

<script>
    import ItemRow from "./ItemRow";

    export default {
        components: {ItemRow},
        props: ['items'],
    }
</script>