<template>
    <v-card class="my-2">
        <v-card-text primary-title>
            {{ entry.item.icon }}
            {{ entry.item.name }}
            {{ entry.amount }}
        </v-card-text>
        <v-card-actions>
            <v-btn :to="`/item/${entry.item.id}`" target="_blank">open</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        props: ["entry"],
        mounted() {
            console.log(this.entry)
        }
    }
</script>