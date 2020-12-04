<template>
    <v-container>

        <item-row v-for="item in items" :item="item" :key="item.id">
            <template v-slot:text>LOL</template>
            <template v-slot:btn>
                <v-btn>kek</v-btn>
            </template>
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