import { createStore } from "vuex"
import getters from "./getters"
import mutations from "./mutation"
import actions from "./actions"

export default createStore({
    state: {
        user: null,
        isLogin: false,
        loadingStatus: false,
    },
    mutations,
    getters,
    actions
})