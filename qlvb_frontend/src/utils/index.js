import { Select } from 'antd';

export const dataToSelectBox = (data = [], key = 'id', name = 'name') => {
    return data.map(record => <Select.Option value={record[key]}>{record[name]}</Select.Option>);
}