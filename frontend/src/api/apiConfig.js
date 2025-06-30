import axios from 'axios';

const API_BASE_URL = 'http://68.221.217.29/api';



const apiClient = axios.create({
    baseURL: '/api',
    headers: {
        'Content-Type': 'application/json',
    },
});


export default apiClient;