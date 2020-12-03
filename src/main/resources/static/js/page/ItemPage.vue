<template>
    <v-app>
        <item-list></item-list>
    </v-app>
</template>
<script>
    import ItemList from "../component/item/ItemList";

    export default {
        components: {ItemList},
        data() {
            return {
                recipe:[]
            }
        }
    }
</script>