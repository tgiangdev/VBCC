
import axios from 'axios';
import { notification } from 'antd';
import Cookies from 'js-cookie';
import proxy from '../../../config/proxy';
import { ACCESS_TOKEN_KEY } from '../constains';

const { REACT_APP_ENV } = process.env;
const SERVER_PATH = proxy[REACT_APP_ENV || 'dev'];
export const BASE_PATH = SERVER_PATH['/api/'].target.replace(/\/+$/, "");

const accessToken = Cookies.get(ACCESS_TOKEN_KEY) || '';

// export const customOptions =  {
//   'apiKey': '',
//   'Content-Type': "application/json; charset=utf-8",
//   'Authorization': accessToken || '',
//   'basePath': BASE_PATH
// }

export const axiosConfig = () => {
    axios.defaults.withCredentials = false;
    axios.defaults.headers.common = {'Authorization': accessToken }
    axios.interceptors.response.use(
      response => response,
      manageErrorConnection
    )
}

function manageErrorConnection(err: any) {
  if (err.response) {
    if (err.response.status === 422) {
      notification.error({
        message: err.response.data.message,
        description: err.response.data.description
      })
    } else if (err.response.status === 500) {
      notification.error({
        message: "Lỗi máy chủ",
        description: err.response.data.description
      })
    } else if (err.response.status === 404) {
      notification.error({
        message: "Bản ghi không tồn tại"
      })
    } else {
      notification.error({
        message: 'Có lỗi xảy ra',
        description: err.response.data.message
      })
    }
  } else {
    notification.error({
      message: "Không thể kết nối tới máy chủ"
    })
  }
  // return Promise.reject(err)
  return err
}