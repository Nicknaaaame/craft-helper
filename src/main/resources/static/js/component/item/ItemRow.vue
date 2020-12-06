<template>
    <v-card class="my-2">
        <v-card-text primary-title>
            {{ item.icon }}
            {{ item.name }}
        </v-card-text>
        <v-card-actions>
            <v-btn :to="`/item/${item.id}`">open</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        props: ['item'],
    }
</script>

