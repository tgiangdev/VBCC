import { Student, StudentListApi } from '@/services/client';
import { PageContainer } from '@ant-design/pro-layout';
import { Button, Card, Drawer, Space, Spin, Table, Select } from 'antd';
import React, { useState, useEffect } from 'react';
import ImportExcel from './import';
import { ImportService } from '@/services/custom-client/ImportService';
import { response } from '@umijs/deps/compiled/express';

const DanhSachTotNghiep: React.FC = () => {
    const [isLoading, setIsLoading] = useState<boolean>(false);
    const [tableData, setTableData] = useState<Student[]>([]);
    const [showFormImport, setShowFormImport] = useState<boolean>(false);
    const [isSaving, setIsSaving] = useState<boolean>(false);
    const importService = new ImportService();
    const studentListClient = new StudentListApi();
    // init
    useEffect(() => {
        setIsLoading(true);
        studentListClient.findAllStudentList()
            .then(resp => {
                if (resp.status === 200) {
                    setTableData(resp.data);
                }
            }).finally(() => setIsLoading(false));
    }, []);

    const toggleShowFormImport = () => {
        setShowFormImport(!showFormImport);
    }
    const onImport = (payload: FormData) => {
        setIsSaving(true);
        importService.importExcel(payload)
            .then(resp => {
                if (resp.status === 200) {
                    toggleShowFormImport();
                }
            })
            .catch(console.log)
            .finally(() => setIsSaving(false))
    }
    return (
        <div>
            <PageContainer extra={
                <><b>Năm tốt nghiệp</b> <Select
                    showSearch
                    style={{ width: 200 }}
                    placeholder="Tất cả"
                // optionFilterProp="children"
                // onChange={onChange}
                // onFocus={onFocus}
                // onBlur={onBlur}
                // onSearch={onSearch}
                // filterOption={(input, option) =>
                //     option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
                // }
                >
                    <Select.Option value="jack">Jack</Select.Option>
                    <Select.Option value="lucy">Lucy</Select.Option>
                    <Select.Option value="tom">Tom</Select.Option>
                </Select>
                </>
            }>
                <Spin spinning={isLoading}>
                    <Card className="fadeInRight">
                        <Space className="button-group">
                            <Button onClick={toggleShowFormImport} type="primary">Import Excel</Button>
                            <Button>Tải file mẫu</Button>
                        </Space>
                        <Table
                            dataSource={tableData}
                            columns={[]}
                            bordered
                        />
                    </Card>
                </Spin>
            </PageContainer>
            <Drawer
                title='Import Excel'
                width={860}
                onClose={toggleShowFormImport}
                visible={showFormImport}
                bodyStyle={{ paddingBottom: 80 }}
                footerStyle={{ textAlign: 'right' }}
                footer={
                    <Space>
                        <Button onClick={toggleShowFormImport}>Huỷ</Button>
                        <Button htmlType="submit" form="form-import-excel" type="primary" loading={isSaving}>
                            Import Excel
                        </Button>
                    </Space>
                }
            >
                <ImportExcel
                    onClose={toggleShowFormImport}
                    onImport={onImport}
                />
            </Drawer>
        </div>
    );
};

export default DanhSachTotNghiep;