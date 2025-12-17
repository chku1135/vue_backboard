import axios from "axios"

const getUserInfo = (userId, userPw) => {
    const reqData = {
        'user_id': userId,
        'user_pw': userPw
    }

    let serverUrl = '//localhost:8081'

    return axios.post(serverUrl + '/user/login', reqData, {
        headers: {
            'Content-type': 'application/json'
        }
    })

    // return {
    //     'data': {
    //         'user_id': reqData.user_id,
    //         'user_token': 'user_test_token',
    //         'user_role': 'ADM'
    //     }
    // }
}

export default {
    async doLogin(user_id, userPw) {
        try{
            const getUserInfoPromise = getUserInfo(userId, userPw)
            const [userInfoResponse] = await Promise.all([getUserInfoPromise])
            if (userInfoResponse.data.length === 0 ){
                return 'notFound'
            } else {
                localStorage.setItem('user_token', userInfoResponse.data.user_token)
                localStorage.setItem('user_role', userInfoResponse.data.user_role)
                return userInfoResponse
            }
        } catch (err) {
            console.error(err)
        }
    }
}