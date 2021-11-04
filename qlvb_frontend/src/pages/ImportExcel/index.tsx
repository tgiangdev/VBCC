import React, { useState } from 'react';
import { PageContainer } from '@ant-design/pro-layout';
import { Card, Form, Upload, Button, Select } from 'antd';
import { FileExcelOutlined, InboxOutlined } from '@ant-design/icons';
import { ImportService } from '@/services/custom-client/ImportService';

const ImportExcel: React.FC = () => {
    const [fileName, setFileName] = useState<string>("");
    const [file, setFile] = useState<any>();

    const formItemLayout = {
        labelCol: { span: 6 },
        wrapperCol: { span: 14 },
    };
    const onFinish = (values: any) => {
        console.log(values)
        const formData = new FormData();
        formData.append('file', file);
        formData.append('info', JSON.stringify(values));

        const client = new ImportService();
        // const inline = new InlineObject();
        client.importExcel(formData)
            .then(console.log)
            .catch(console.log)
    }
    const normFile = (e: any) => {
        console.log('Upload event:', e);
        if (Array.isArray(e)) {
            return e.length > 0 ? [e[e.length - 1]] : [];
        }
        // return e && e.fileList;
        return e && e.fileList.length > 0 ? [e.fileList[e.fileList.length - 1]] : [];
    };
    const beforeUpload = (f: any) => {
        setFileName(f ? f.name : '');
        setFile(f)
        return false;
    }
    return (
        <PageContainer>
            <Card className="fadeInRight">
                <Form
                    name="validate_other"
                    {...formItemLayout}
                    onFinish={onFinish}
                >
                    <Form.Item
                        name="clazz"
                        label="Lớp"
                        rules={[{ required: true, message: 'Please select!' }]}
                    >
                        <Select placeholder="Lớp">
                            <Select.Option value="10a1">10A1</Select.Option>
                            <Select.Option value="10a2">10A2</Select.Option>
                            <Select.Option value="10a3">10A3</Select.Option>
                        </Select>
                    </Form.Item>
                    <Form.Item
                        name="year"
                        label="Năm học"
                        rules={[{ required: true, message: 'Please select!' }]}
                    >
                        <Select placeholder="Năm học">
                            <Select.Option value="2020">2020-2021</Select.Option>
                            <Select.Option value="2021">2021-2022</Select.Option>
                            <Select.Option value="2022">2022-2023</Select.Option>
                        </Select>
                    </Form.Item>
                    <Form.Item
                        name="file"
                        label="File"
                        rules={[{ required: true, message: 'Please select file!' }]}
                    >
                        <Form.Item name="file" valuePropName="fileList" getValueFromEvent={normFile} noStyle>
                            <Upload.Dragger
                                name="files"
                                multiple={false}
                                showUploadList={false}
                                beforeUpload={beforeUpload}
                            >

                                {fileName ? <>
                                    <p className="ant-upload-drag-icon"><FileExcelOutlined /></p>
                                    <p className="ant-upload-text">{fileName}</p>
                                </>
                                    :
                                    <>
                                        <p className="ant-upload-drag-icon">
                                            <InboxOutlined />
                                        </p>
                                        <p className="ant-upload-text">Click or drag file to this area to upload</p>
                                        <p className="ant-upload-hint">Support for a single or bulk upload.</p>
                                    </>}
                            </Upload.Dragger>
                        </Form.Item>
                    </Form.Item>

                    <Form.Item wrapperCol={{ span: 12, offset: 6 }} >
                        <Button type="primary" htmlType="submit">
                            Submit
                        </Button>
                    </Form.Item>
                </Form>
            </Card>
        </PageContainer>
    );
};
export default ImportExcel;