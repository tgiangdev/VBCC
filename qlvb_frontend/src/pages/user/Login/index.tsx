import {
  LockOutlined,
  UserOutlined,
} from '@ant-design/icons';
import React, { useState } from 'react';
import { history, FormattedMessage, useModel } from 'umi';
import Footer from '@/components/Footer';
import { Form, Input, Button, Checkbox } from 'antd';
import styles from './index.less';
import { ACCESS_TOKEN_KEY } from '@/core/constains';
import Cookies from 'js-cookie';
import type { LoginPayload } from '@/services/client';
import { AuthApi } from '@/services/client';

const Login: React.FC = () => {
  const { initialState, setInitialState } = useModel('@@initialState');
  const [isLogging, setIsLogging] = useState<boolean>(false);


  const fetchUserInfo = async () => {
    const initData = await initialState?.fetchInitData?.();
    if (initData) {
      await setInitialState((s) => ({
        ...s,
        ...initData,
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
        Cookies.set(ACCESS_TOKEN_KEY, msg.data.accessToken);

        await fetchUserInfo();
        if (!history) return;
        const { query } = history.location;
        const { redirect } = query as { redirect: string };
        history.push(redirect || '/');
        return;
      }
      setIsLogging(false);
    } catch (error) {
      setIsLogging(false);
    }
  };

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
          autoComplete="off"
          className={styles.form_login}
        >
          <div className={styles.title}>ĐĂNG NHẬP</div>
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
