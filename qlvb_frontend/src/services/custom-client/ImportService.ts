import axios from 'axios';
import axiosRequest from "@/core/interceptors/axios";

export class ImportService {
    public importExcel(fromData: FormData) {
        return axios.post('/api/student/import', fromData, {
            headers: axiosRequest,
            withCredentials: false
        });
        // return axios({
        //     method: 'POST',
        //     url : '/api/student/import',
        //     data: fromData,
        //     headers: axiosRequest,
        //     withCredentials: false
        // })
    }
}