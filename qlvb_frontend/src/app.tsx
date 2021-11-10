import type { Settings as LayoutSettings } from '@ant-design/pro-layout';
import { PageLoading } from '@ant-design/pro-layout';
import type { RunTimeLayoutConfig } from 'umi';
import { history } from 'umi';
import RightContent from '@/components/RightContent';
import Footer from '@/components/Footer';
import { BookOutlined, LinkOutlined } from '@ant-design/icons';
import Cookies from 'js-cookie';
import { ACCESS_TOKEN_KEY } from './core/constains';
import jwt_decode from 'jwt-decode';
import { SchoolApi, SchoolYearApi, SharedDirectoryApi } from './services/client';

const isDev = process.env.NODE_ENV === 'development';
const loginPath = '/user/login';

export const initialStateConfig = {
  loading: <PageLoading />,
};

/**
 * @see  https://umijs.org/zh-CN/plugins/plugin-initial-state
 * */
export async function getInitialState(): Promise<{
  settings?: Partial<LayoutSettings>;
  currentUser?: any;
  globalData?: any;
  fetchUserInfo?: () => any | undefined;
}> {
  const fetchUserInfo = async () => {
    try {
      const accessToken = Cookies.get(ACCESS_TOKEN_KEY);
      const tokenData: any = jwt_decode(accessToken);
      const currentUser = JSON.parse(tokenData.account);
      return currentUser;
    } catch (error) {
      history.push(loginPath);
    }
    return undefined;
  };
  const fetchInitData = async () => {
    const schoolYearClient = new SchoolYearApi();
    const sharedDirectory = new SharedDirectoryApi();

    const schoolYears = await schoolYearClient.findAllSchoolYear();
    const sharedDirectories = await sharedDirectory.findAllSharedDirectory();
    return {
      schoolYears,
      sharedDirectories
    }
  }
  if (history.location.pathname !== loginPath) {
    const currentUser = await fetchUserInfo();
    const globalData = await fetchInitData();
    return {
      fetchUserInfo,
      currentUser,
      globalData,
      settings: {},
    };
  }
  return {
    fetchUserInfo,
    settings: {},
  };
}

// ProLayout 支持的api https://procomponents.ant.design/components/layout
export const layout: RunTimeLayoutConfig = ({ initialState }) => {
  return {
    rightContentRender: () => <RightContent />,
    disableContentMargin: false,
    // waterMarkProps: {
    //   content: initialState?.currentUser?.name,
    // },
    footerRender: () => <Footer />,
    onPageChange: () => {
      const { location } = history;
      if (!initialState?.currentUser && location.pathname !== loginPath) {
        history.push(loginPath);
      }
    },
    links: isDev
      ? [
        <a href="http://localhost:8001/swagger-ui/index.html?url=/v3/api-docs" target="_blank">
          <LinkOutlined />
          <span>OpenAPI</span>
        </a>,
        <a href="https://ant.design/components/overview/" target="_blank">
          <BookOutlined />
          <span>Doc AntD</span>
        </a>,
      ]
      : [],
    menuHeaderRender: undefined,
    // 自定义 403 页面
    // unAccessible: <div>unAccessible</div>,
    ...initialState?.settings,
  };
};
