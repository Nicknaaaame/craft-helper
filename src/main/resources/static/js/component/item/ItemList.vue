<template>
    <v-container>
        <v-text-field
                label="Find item"
                v-model="searchName"
                @change="findItem"
        ></v-text-field>
        <div v-for="item in findItem()" :item="item" :key="item.id">
            <item-row :item="item">
                <slot v-for="(_, name) in $slots" :name="name" :slot="name"/>
                <template v-for="(_, name) in $scopedSlots" :slot="name" slot-scope="slotData">
                    <slot :name="name" v-bind="slotData"/>
                </template>
            </item-row>
        </div>
    </v-container>
</template>

<script>
    import ItemRow from "./ItemRow";

    export default {
        components: {ItemRow},
        props: ['items'],
        data() {
            return {
                searchName: "",
            }
        },
        methods: {
            findItem() {
                let foundedItems = []
                if (this.searchName !== "") {
                    this.items.forEach(item => {
                        if (item.name.toLowerCase().includes(this.searchName.toLowerCase()))
                            foundedItems.push(item)
                    })
                } else {
                    foundedItems = this.items
                }
                return foundedItems
            }
        }
    }
</script>