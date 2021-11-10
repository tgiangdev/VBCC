import axios from 'axios';

export class ImportService {
    public importExcel(fromData: FormData) {
        return axios.post('/api/student-list/import', fromData, {
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