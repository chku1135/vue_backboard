import { USER_ID, IS_AUTH, ERROR_STATE } from "./mutation_types";
import LoginAPI from "@/service/LoginAPI";

let setUserId = ({commit}, data) => {
    commit(USER_ID, data)
}

let setErrorState = ({commit}, data) => {
    commit(ERROR_STATE, data)
}

let setIsAuth = ({commit}, data) => {
    commit(IS_AUTH, data)
}

//백엔드 반환 결과값 > 로그인 성공/실패 여부 vuex에 전송
let processResponse = (store, loginResponse) => {
    switch (loginResponse) {
        case 'notFound':
            setErrorState(store, 'Wrong ID of Password')
            setIsAuth(store, false)
            break
        default:
            setUserId(store, loginResponse.user_id)
            setErrorState(store, '')
            setIsAuth(store, true)
    }
}

export default {
    async login (store, {user_id, user_pw}) {
        let loginResponse = await LoginAPI.doLogin(user_id, user_pw)
        processResponse(store, loginResponse)
        return store.getters.getIsAuth
    }
}