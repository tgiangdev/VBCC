import React, { useState } from 'react';
import { Form, Upload, Select, Input, DatePicker } from 'antd';
import { FileExcelOutlined, InboxOutlined } from '@ant-design/icons';
import type { SchoolYear } from '@/services/client';
import { dataToSelectBox } from '@/utils';

const formItemLayout = {
    labelCol: { span: 6 },
    wrapperCol: { span: 14 },
};

const ImportExcel: React.FC<Props> = (props: Props) => {
    const [fileName, setFileName] = useState<string>("");
    const [file, setFile] = useState<any>();

    const onFinish = (values: any) => {
        console.log(values)
        const formData = new FormData();
        formData.append('file', file);
        formData.append('info', JSON.stringify(values));

        props.onImport(formData);
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
        <Form
            name="form-import-excel"
            {...formItemLayout}
            onFinish={onFinish}
        >
            <Form.Item
                name="schoolYearId"
                label="Năm học"
                rules={[{ required: true, message: 'Trường này không được để trống!' }]}
            >
                <Select placeholder="Năm học">
                    {dataToSelectBox(props.schoolYears, 'id', 'code')}
                </Select>
            </Form.Item>
            <Form.Item
                name="decisionNumber"
                label="Số quyết định tốt nghiệp"
                rules={[{ required: true, message: 'Trường này không được để trống!' }]}
            >
                <Input />
            </Form.Item>
            <Form.Item
                name="dateOfDecision"
                label="Ngày quyết định tốt nghiệp"
                rules={[{ required: true, message: 'Trường này không được để trống!' }]}
            >
                <DatePicker />
            </Form.Item>
            <Form.Item
                name="graduationExam"
                label="Tên kỳ quyết định"
                rules={[{ required: true, message: 'Trường này không được để trống!' }]}
            >
                <Input />
            </Form.Item>
            <Form.Item
                name="graduationCourse"
                label="Tên khoá quyết định"
                rules={[{ required: true, message: 'Trường này không được để trống!' }]}
            >
                <Input />
            </Form.Item>
            <Form.Item
                name="file"
                label="File"
                rules={[{ required: true, message: 'Trường này không được để trống file!' }]}
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

            {/* <Form.Item wrapperCol={{ span: 12, offset: 6 }} >
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
            </Form.Item> */}
        </Form>
    );
};
type Props = {
    schoolYears: SchoolYear[]
    onClose: () => void,
    onImport: (payload: FormData) => void
}
export default ImportExcel;