<template>
    <v-container>
        <v-card @click.native.stop flat class="d-inline pa-2" tile>
            <v-checkbox :value="isFullAmount" class="d-inline-flex"
                        @change="isFullAmount?currAmount=0:currAmount=resultAmount"
            />
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
        props: ["entry", "resultAmount", 'parentTree', 'parentRow', 'parentIndex'],
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
                let tree = this.parent.$parent.$parent.$parent.$parent
                if(this.parent.$props.amount!==0)
                    this.currAmount = (this.resultAmount / this.parentTree.amount) * this.parent.currAmount
                else
                    this.currAmount = 0
                // console.log(this.parent.currAmount)
            }
        },
        mounted() {
            console.log(this.parentTree)
            // console.log(this.parent.$parent.$parent.$parent.$parent.$props)
            // console.log(this.parent)
            // let par = this.$parent.$parent.$parent.$parent.$parent.$parent
            // let par = this.$parent.$parent.$parent.$parent.$parent.$parent.$children[0].$children[0].$data.currAmount
            // console.log(par.currAmount)
            /*let i = 0;
            while (par) {
                if (par.$children[0].$children[0].$data.currAmount !== undefined) {
                    console.log(par.$children[0].$children[0].$data.currAmount, i)
                    console.log(par.$children[0], i)
                }
                i++
                par = par.$parent
            }*/
            /*for (let i = 0; i < 7; i++) {
                par = par.$parent
                console.log(i)
            }
            console.log(par.$children[0].$children[0].$data.currAmount)*/
        }
    }
</script>