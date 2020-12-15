<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>
                <router-link to="/" tag="span" style="cursor: pointer">
                    {{ appTitle }}
                </router-link>
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items>
                <v-btn
                        v-for="item in menuItems"
                        :key="item.title"
                        :to="item.path">
                    <v-icon left dark>{{ item.icon }}</v-icon>
                    {{ item.title }}
                </v-btn>
            </v-toolbar-items>
        </v-app-bar>
        <v-main>
            <router-view></router-view>
        </v-main>
    </v-app>
</template>

<script>

    export default {
        name: "App",
        data() {
            return {
                appTitle: 'Craft helper',
                menuItems: [
                    {title: 'Home', path: '/', icon: 'home'},
                    {title: 'Add item', path: '/additem', icon: 'face'},
                ]
            }
        },
        beforeCreate() {
            this.$store.dispatch('initState')
        }
    }
</script>

<style>

</style>
