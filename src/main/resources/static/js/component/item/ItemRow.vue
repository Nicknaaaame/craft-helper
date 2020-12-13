<template>
    <v-card class="my-2">
        <v-card-text primary-title>
            <v-img v-if="item.icon" :src="`data:image/jpg;base64, ${item.icon}`" height="65" width="65"/>
            {{ item.name }}
            <slot name="text"/>
        </v-card-text>
        <v-card-actions>
            <slot name="actions"/>
        </v-card-actions>
        <slot name="content"/>
    </v-card>
</template>

<script>
    export default {
        props: ['item'],

    }
</script>

