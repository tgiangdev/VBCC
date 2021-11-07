import type { Account } from '@/services/client';
import { AccountRoleEnum, UserApi } from '@/services/client';
import { DeleteOutlined, EditOutlined } from '@ant-design/icons';
import { PageContainer } from '@ant-design/pro-layout';
import { Button, Card, Drawer, Popconfirm, Space, Table, Spin } from 'antd';
import React, { useState, useEffect } from 'react';
import defineColumns from './columns';
import EditFormAccount from './edit';
import { useModel } from 'umi';

const User: React.FC = () => {
    const { initialState } = useModel('@@initialState');
    const currentUser: Account = initialState?.currentUser;
    const userCLient = new UserApi()
    const [tableData, setTableData] = useState<Account[]>([]);
    const [showEdit, setShowEdit] = useState<boolean>(false);
    const [accountEdit, setAccountEdit] = useState<Account>();
    const [isLoading, setIsLoading] = useState<boolean>(false);

    const listRole = [{ value: 'USER', label: 'Giáo viên' }];
    if (currentUser.role === AccountRoleEnum.System) {
        listRole.push({ value: 'ADMIN', label: 'Quản trị viên' })
    }
    useEffect(() => {
        setIsLoading(true);
        userCLient.findAllUser().then(res => {
            if (res.status === 200) {
                setTableData(res.data)
            }
            setIsLoading(false);
        });
    }, []);

    const toggleShowEdit = () => {
        setShowEdit(!showEdit);
        setAccountEdit(undefined);
    }
    const handleCreate = () => {
        setAccountEdit(undefined);
        toggleShowEdit();
    }
    const handleEdit = (account: Account) => {
        console.log(account);
        toggleShowEdit();
        setAccountEdit(account);
    }
    const onEdit = (values: Account) => {
        console.log('onEdit', values)
        if (accountEdit) {
            // update
            console.log(accountEdit.id!);
            userCLient.updateUser(accountEdit.id!, values).then(res => {
                if (res.status === 200) {
                    console.log(res.data)
                    const newAccount: any = res.data;
                    const index = tableData.findIndex(record => record.id === newAccount.id);
                    tableData.splice(index, 1, newAccount)
                    setTableData([...tableData]);
                    toggleShowEdit();
                }
            });
        } else {
            userCLient.createUser(values).then(res => {
                if (res.status === 200) {
                    console.log(res.data)
                    const newAccount: any = res.data;
                    tableData.push(newAccount)
                    setTableData([...tableData]);
                    toggleShowEdit();
                }
            });
        }
    }
    const onDelete = (id: number) => {
        console.log(id);
        userCLient.deleteUser(id).then(() => setTableData(tableData.filter(r => r.id !== id)));
    }

    const action = (id: number, record: Account): React.ReactNode => {
        if (currentUser.role!.length <= record.role!.length) {
            return <></>
        }
        return <Space key={id}>
            <Button size="small" onClick={() => handleEdit(record)}><EditOutlined /></Button>
            <Popconfirm
                title="Bạn chắc chắn muốn xóa?"
                onConfirm={() => onDelete(id)}
                okText="Đồng ý"
                cancelText="Hủy bỏ"
            >
                <Button size="small"><DeleteOutlined style={{ color: 'red' }} /></Button>
            </Popconfirm>
        </Space>
    }

    const columns: any[] = defineColumns(action);

    return (
        <div>
            <PageContainer>
                <Spin spinning={isLoading}>
                    <Card className="fadeInRight">
                        <Space className="button-group">
                            <Button onClick={handleCreate} type="primary">Thêm mới</Button>
                        </Space>
                        <Table
                            dataSource={tableData}
                            columns={columns}
                            bordered
                        />
                    </Card>
                </Spin>
            </PageContainer>
            <Drawer
                title={accountEdit ? 'Sửa thông tin' : 'Tạo mới tài khoản'}
                width={720}
                onClose={toggleShowEdit}
                visible={showEdit}
                bodyStyle={{ paddingBottom: 80 }}
                footerStyle={{ textAlign: 'right' }}
                footer={
                    <Space>
                        <Button onClick={toggleShowEdit}>Huỷ</Button>
                        <Button htmlType="submit" form="form-create-account" type="primary">
                            Lưu
                        </Button>
                    </Space>
                }
            >
                <EditFormAccount
                    account={accountEdit}
                    onEdit={onEdit}
                    listRole={listRole}
                />
            </Drawer>
        </div>
    );
};
export default User;