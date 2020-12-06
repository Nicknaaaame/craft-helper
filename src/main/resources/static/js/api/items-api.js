import Vue from 'vue'

const items = Vue.resource('/api/item{/id}')

export default {
    get: id => items.get({id}),
}
