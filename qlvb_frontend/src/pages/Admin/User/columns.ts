import type { Account } from "@/services/client";

export default (action: (id: any, account: Account) => React.ReactNode) => [
    {
        title: 'Account',
        dataIndex: 'username',
        key: 'username',
    },
    {
        title: 'Email',
        dataIndex: 'email',
        key: 'email',
    },
    {
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: 'Action',
        dataIndex: 'id',
        align: 'center',
        width: '80px',
        key: 'id',
        // render: (id: any, row: any) => {
        //     return <>
        //         <Button onClick={() => onEdit(id)}><EditOutlined /></Button>
        //     </>
        // }
        render: (id: any, account: Account) => action(id, account)
    },
];