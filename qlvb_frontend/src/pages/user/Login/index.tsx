import {
  LockOutlined,
  UserOutlined,
} from '@ant-design/icons';
import { Alert, message } from 'antd';
import React, { useState } from 'react';
import { useIntl, history, FormattedMessage, useModel } from 'umi';
import Footer from '@/components/Footer';
import { login } from '@/services/ant-design-pro/api';
import { Form, Input, Button, Checkbox } from 'antd';
import styles from './index.less';
import { AuthApi, LoginPayload } from '@/services/client';
import { ACCESS_TOKEN_KEY } from '@/core/constains';
import Cookies from 'js-cookie';

const LoginMessage: React.FC<{
  content: string;
}> = ({ content }) => (
  <Alert
    style={{
      marginBottom: 24,
    }}
    message={content}
    type="error"
    showIcon
  />
);

const Login: React.FC = () => {
  const [userLoginState, setUserLoginState] = useState<API.LoginResult>({});
  const { initialState, setInitialState } = useModel('@@initialState');
  const [isLogging, setIsLogging] = useState<boolean>(false);

  const intl = useIntl();

  const fetchUserInfo = async () => {
    const userInfo = await initialState?.fetchUserInfo?.();
    console.log('userInfo', userInfo)
    if (userInfo) {
      await setInitialState((s) => ({
        ...s,
        currentUser: userInfo,
      }));
    }
  };

  const handleSubmit = async (values: LoginPayload) => {
    try {
      setIsLogging(true);
      const authApi = new AuthApi();
      const msg: any = await authApi.login({ ...values });
      console.log(msg);
      if (msg.status === 200) {
        // const defaultLoginSuccessMessage = intl.formatMessage({
        //   id: 'pages.login.success',
        //   defaultMessage: '登录成功！',
        // });
        // message.success(defaultLoginSuccessMessage);
        console.log(msg.data)
        Cookies.set(ACCESS_TOKEN_KEY, msg.data.accessToken);

        await fetchUserInfo();
        if (!history) return;
        const { query } = history.location;
        const { redirect } = query as { redirect: string };
        history.push(redirect || '/');
        return;
      }
      setUserLoginState(msg);
      setIsLogging(false);
    } catch (error) {
      // const defaultLoginFailureMessage = intl.formatMessage({
      //   id: 'pages.login.failure',
      //   defaultMessage: '登录失败，请重试！',
      // });
      // message.error(defaultLoginFailureMessage);
      setIsLogging(false);
    }
  };
  const { status, type: loginType } = userLoginState;

  return (
    <div className={styles.container}>
      {/* <div className={styles.lang} data-lang>
        {SelectLang && <SelectLang />}
      </div> */}
      <div className={styles.content}>
        <Form
          name="login-form"
          initialValues={{ remember: true }}
          onFinish={handleSubmit}
          // onFinishFailed={onFinishFailed}
          autoComplete="off"
          className={styles.form_login}
        >
          <div className={styles.title}>ĐĂNG NHẬP</div>

          {status === 'error' && loginType === 'account' && (
            <LoginMessage
              content={intl.formatMessage({
                id: 'pages.login.accountLogin.errorMessage',
                defaultMessage: '账户或密码错误(admin/ant.design)',
              })}
            />
          )}
          <Form.Item
            name="username"
            rules={[{ required: true, message: 'Please input your username!' }]}
          >
            <Input prefix={<UserOutlined className="site-form-item-icon" />} size="large" />
          </Form.Item>

          <Form.Item
            name="password"
            rules={[{ required: true, message: 'Please input your password!' }]}
          >
            <Input.Password prefix={<LockOutlined className="site-form-item-icon" />} size="large" />
          </Form.Item>
          <div
            style={{
              marginBottom: 24,
            }}
          >
            <Form.Item>
              <Button loading={isLogging} type="primary" htmlType="submit" style={{ width: '100%' }} size="large">
                Submit
              </Button>
            </Form.Item>
            <Form.Item name="remember" valuePropName="checked">
              <Checkbox>Remember me</Checkbox>
              <a
                style={{
                  float: 'right',
                }}
              >
                <FormattedMessage id="pages.login.forgotPassword" defaultMessage="忘记密码" />
              </a>
            </Form.Item>

          </div>

        </Form>
      </div>
      <Footer />
    </div>
  );
};

export default Login;
