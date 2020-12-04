<template>
    <v-container>
        <v-text-field
                label="Find item"
                v-model="searchName"
                @change="findItem"
        ></v-text-field>
        <item-row v-for="item in foundedItems" :item="item" :key="item.id">
            <slot v-for="slot in Object.keys($slots)" :name="slot" :slot="slot"/>
            <!--<template v-slot:text>LOL</template>
            <template v-slot:btn>
                <v-btn>kek</v-btn>
            </template>-->
        </item-row>
    </v-container>
</template>

<script>
    import ItemRow from "./ItemRow";
    import item from "../../util/item";

    export default {
        components: {ItemRow},
        props: ['items'],
        data() {
            return {
                foundedItems: [],
                searchName: ""
            }
        },
        methods: {
            findItem() {
                console.log(this.foundedItems)
                if (this.searchName !== "") {
                    this.foundedItems = []
                    this.items.forEach(item => {
                        if (item.name.toLowerCase().includes(this.searchName.toLowerCase()))
                            this.foundedItems.push(item)
                    })
                } else
                    this.foundedItems = this.items
            }
        },
        created() {
            setTimeout(()=>{
                this.foundedItems = this.items
                // console.log(this.items)
            }, 5)
            //TODO: Resolve this problem (js is peace of shit)
        }
    }
</script>