<template>
    <v-card class="my-2">
        <v-card-text primary-title>
            {{ item.icon }}
            {{ item.name }}
            <slot name="text"/>
        </v-card-text>
        <v-card-actions>
            <v-btn :to="`/item/${item.id}`">open</v-btn>
            <slot name="btn"/>
        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        props: ['item'],
    }
</script>

