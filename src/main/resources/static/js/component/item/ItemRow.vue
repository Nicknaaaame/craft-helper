<template>
    <v-card class="my-2">

        <v-card-text primary-title>
            <v-img v-if="item.icon" :src="`data:image/jpg;base64, ${item.icon}`" height="65" width="65"/>
            {{ item.name }}
        </v-card-text>
        <v-card-actions>
            <v-btn :to="`/item/${item.id}`">open</v-btn>
            <v-btn @click="del(item.id)">delete</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    import api from "backend-api"
    export default {
        props: ['item'],
        methods: {
            del(id) {
                api.deleteItem(id)
            }
        }
    }
</script>

