import Vue from 'vue'
import Vuex from 'vuex'
import api from 'backend-api'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        items: []
    },
    getters: {
        getItems: state => state.items
    },

    mutations: {
        addItemMutation(state, item) {
            state.items = [
                ...state.items,
                item
            ]
        },
        updateItemMutation(state, item) {
            const updateIndex = state.messages.findIndex(el => el.id === item.id)
            state.items = [
                ...state.items.slice(0, updateIndex),
                item,
                ...state.items.slice(updateIndex + 1)
            ]
        },
    },
    actions: {
        async addItemAction({commit}, item) {
            const result = await api.saveItem(item)
            commit('addMessageMutation', result.data)
        },
        async initState({state}) {
            const result = await api.getAllItems()
            console.log(result)
            state.items = result.data
            console.log(state)
        }
    }
})