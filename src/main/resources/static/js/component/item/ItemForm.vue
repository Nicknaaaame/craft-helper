<template>
    <v-app>
        <v-form>
            <v-container>
                <v-text-field
                        v-model="name"
                        :counter="10"
                        label="Name"
                        required
                ></v-text-field>
                <v-file-input
                        v-model="icon"
                        accept="image/*"
                        label="Icon input"
                        :rules="fileRules"
                ></v-file-input>
            </v-container>
        </v-form>
    </v-app>
</template>

<script>
    export default {
        data() {
            return{
                fileRules: [
                    v => !!v || 'File is required',
                    v => (v && v.length > 0) || 'File is required',
                ],
                name:"",
                icon,
            }
        }
    }
</script>

<style>

</style>