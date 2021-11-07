import React, { useEffect } from 'react';
import type { Account } from '@/services/client';
import { Form, Select, Input, Row, Col } from 'antd';
import type { FormInstance } from 'antd/es/form';

const formItemLayout = {
    labelCol: { span: 6 },
    wrapperCol: { span: 14 },
};
const EditFormAccount: React.FC<Props> = (props: Props) => {
    const formRef = React.createRef<FormInstance>();

    const onFill = () => {
        if (props.account) {
            formRef.current!.setFieldsValue(props.account || {});
        } else {
            formRef.current!.resetFields();
        }
    };

    useEffect(() => {
        onFill();
    }, [props.account]);

    return (
        <div>
            <Form
                name="form-create-account"
                {...formItemLayout}
                onFinish={props.onEdit}
                ref={formRef}
            >
                <Row>
                    <Col span={12}>
                        <Form.Item
                            name="username"
                            label="Tài khoản"
                            rules={[{ required: true, message: 'Trường này không được để trống!' }]}
                        >
                            <Input disabled={!!props.account} />
                        </Form.Item>
                    </Col>
                    <Col span={12}>
                        <Form.Item
                            name="name"
                            label="Họ tên"
                            rules={[{ required: true, message: 'Trường này không được để trống!' }]}
                        >
                            <Input />
                        </Form.Item>
                    </Col>
                    <Col span={12}>
                        <Form.Item
                            name="email"
                            label="Email"
                            rules={[{ type: 'email', message: 'Email không đúng định dạng!' }]}
                        >
                            <Input />
                        </Form.Item>
                    </Col>
                    <Col span={12}>
                        <Form.Item
                            name="role"
                            label="Chức vụ"
                            rules={[{ required: true, message: 'Please select!' }]}
                        >
                            <Select placeholder="Chức vụ">
                                {
                                    props.listRole.map(r => <Select.Option value={r.value} key={r.value}>{r.label}</Select.Option>)
                                }
                            </Select>
                        </Form.Item>
                    </Col>
                </Row>
            </Form>
        </div>
    );
};

type Props = {
    account?: Account,
    onEdit: (account: Account) => void,
    listRole: any[]
}
export default EditFormAccount;