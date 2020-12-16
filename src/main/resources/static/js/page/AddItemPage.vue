<template>
    <v-container>
        <item- :items="getItems"/>
    </v-container>
</template>

<script>
    import ItemForm from "../component/item/ItemForm";
    import api from "../backend-api";
    import {mapGetters} from "vuex";

    export default {
        components: {ItemForm},
        computed: mapGetters(['getItems']),
    }
</script>