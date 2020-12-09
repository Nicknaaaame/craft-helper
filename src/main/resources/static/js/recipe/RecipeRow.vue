<template>
    <v-container>
        <v-card @click.native.stop flat class="d-inline pa-2" tile>
            <v-checkbox :value="isFullAmount" class="d-inline-flex"
                        @change="isFullAmount?currAmount=0:currAmount=resultAmount"/>
            {{ entry.item.icon }}
            {{ entry.item.name }}
            {{currAmount}}/
            {{resultAmount}}
            <v-btn :disabled="isFullAmount" @click="currAmount++">+</v-btn>
            <v-btn :disabled="currAmount===0" @click="currAmount--">-</v-btn>
        </v-card>
    </v-container>
</template>

<script>
    export default {
        name: "recipeRow",
        props: ["entry", "resultAmount", 'parentAmount'],
        data() {
            return {
                currAmount: 0,
                isFullAmount: this.currAmount === this.resultAmount,
                parent: this.$parent.$parent.$parent.$parent.$parent.$parent.$children[0].$children[0]
            }
        },
        watch: {
            'currAmount'() {
                this.isFullAmount = this.currAmount === this.resultAmount
            },
            'parent.$data.currAmount'(){
                if(this.parent.$props.amount!==0)
                    this.currAmount = (this.resultAmount / this.parentAmount) * this.parent.currAmount
                else
                    this.currAmount = 0
            }
        },
    }
</script>