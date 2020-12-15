import Vue from 'vue'
import Vuex from 'vuex'
import api from 'backend-api'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        items: []
    },
    getters: {
        getItems: state => state.items,
        getItemById: state => id => state.items.find(el => el.id === parseInt(id))
    },

    mutations: {
        addItemMutation(state, item) {
            state.items = [
                ...state.items,
                item
            ]
        },
        updateItemMutation(state, item) {
            const updateIndex = state.items.findIndex(el => el.id === item.id)
            state.items = [
                ...state.items.slice(0, updateIndex),
                item,
                ...state.items.slice(updateIndex + 1)
            ]
        },
        deleteItemMutation(state, id) {
            const deletionIndex = state.items.findIndex(el => el.id === id)

            if (deletionIndex > -1) {
                state.items = [
                    ...state.items.slice(0, deletionIndex),
                    ...state.items.slice(deletionIndex + 1)
                ]
            }
        },
    },
    actions: {
        async addItemAction({commit}, item) {
            const result = await api.saveItem(item)
            commit('addItemMutation', result.data)
            return result
        },
        async updateItemAction({commit}, item) {
            const result = await api.updateItem(item)
            commit('updateItemMutation', result.data)
            return result
        },
        async deleteItemAction({commit}, id) {
            const result = await api.deleteItem(id)
            if (result.status === 200) {
                commit('deleteItemMutation', id)
            }
            return result
        },

        async initState({state}) {
            const result = await api.getAllItems()
            state.items = result.data
        }
    }
})