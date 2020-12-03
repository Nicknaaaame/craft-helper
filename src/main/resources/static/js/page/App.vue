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
                appTitle: 'Awesome App',
                menuItems: [
                    {title: 'Home', path: '/', icon: 'home'},
                    {title: 'Item', path: '/itemform', icon: 'face'},
                    // {title: 'Sign Up', path: '/signup', icon: 'face'},
                    // {title: 'Sign In', path: '/signin', icon: 'lock_open'}
                ]
            }
        },
    };
</script>

<style>

</style>
