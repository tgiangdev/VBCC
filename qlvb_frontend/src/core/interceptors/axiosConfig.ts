
import axios from 'axios';
import { notification } from 'antd';
import Cookies from 'js-cookie';
import proxy from '../../../config/proxy';
import { ACCESS_TOKEN_KEY } from '../constains';
import { history } from 'umi';

const loginPath = '/user/login';
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
      handleResponse,
      handleError
    )
}

function handleResponse(response: any) {
  if(response.status === 201) {
    notification.success({
      message: 'Lưu dữ liệu thành công'
    })
  } else if(response.status === 204) {
    notification.success({
      message: 'Xoá thành công'
    })
  }
  return response;
}

function handleError(err: any) {
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
    } else if (err.response.status === 403) {
      notification.error({
        message: "Quyền truy cập đã hết hạn"
      });
      history.push(loginPath);
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