import { ElMessage } from 'element-plus'
import router from '../router'
import axios from "axios";

const request = axios.create({
    baseURL: import.meta.env.VITE_BASE_URL,
    timeout: 30000  // Background interface timeout settings
})

// request interceptor
// Can do some processing of a request before it is sent.
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    return config
}, error => {
    return Promise.reject(error)
});

// response interceptor
// Can unify the results after an interface response
request.interceptors.response.use(
    response => {
        let res = response.data;
        // If it is a returned file
        if (response.config.responseType === 'blob') {
            return res
        }
        // Compatible with server returned string data
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        // Prompts when permissions are not validated.
        if (res.code === '401') {
            ElMessage.error(res.msg);
            router.push("/login")
        }
        return res;
    },
        error => {
        console.log('err' + error)
        return Promise.reject(error)
    }
)

export default request