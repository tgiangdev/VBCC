import proxy from '../../../config/proxy';

const { REACT_APP_ENV } = process.env;
const SERVER_PATH = proxy[REACT_APP_ENV || 'dev'];
const BASE_PATH = SERVER_PATH['/api/'].target.replace(/\/+$/, "");

const accessToken = localStorage.getItem('accessToken');

export default {
  'apiKey': '',
  'Content-Type': "application/json; charset=utf-8",
  'Authorization': accessToken || '',
  'basePath': BASE_PATH
}