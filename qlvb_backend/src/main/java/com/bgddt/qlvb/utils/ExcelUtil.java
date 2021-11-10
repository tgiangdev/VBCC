package com.bgddt.qlvb.utils;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.services.excel.CellInfo;
import com.bgddt.qlvb.services.excel.CellValueType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.util.CellReference;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class ExcelUtil {
    public static final DecimalFormat df = new DecimalFormat("###.#");

    public static String getCellValueAndValidate(Cell cell, FormulaEvaluator evaluator, CellInfo cellInfo) throws BusinessException {
        try {
            String cellValue = getCellValue(cell, evaluator, cellInfo);
            validateCell(cellInfo, cellValue);
            return cellValue;
        } catch (Exception e) {
            throw new BusinessException( String.format("Lỗi đọc file dòng %s, cột %s: %s",
                            cell.getRowIndex() + 2,
                            CellReference.convertNumToColString(cell.getColumnIndex()),
                            e.getMessage())
            );
        }
    }

    private static void validateCell(CellInfo cellInfo, String value) throws BusinessException {
        if(value == null || value.isEmpty()) return;
        // check length
        if(cellInfo.getMaxLength() > 0 && value.length() > cellInfo.getMaxLength()) {
            throw new BusinessException(String.format("Vượt quá độ dài cho phép(%s)", cellInfo.getMaxLength()));
        }

        // Check list in
        if(cellInfo.getCellType() == CellValueType.KEY_VALUE && cellInfo.getValueIn() != null) {
            if(!cellInfo.getValueIn().contains(value)) {
                throw new BusinessException(String.format("Chỉ nhận giá trị (%s)", String.join(",",cellInfo.getValueIn())));
            }
        }

        // check Kiểu dữ liệu
        if(cellInfo.getCellType() == CellValueType.FULL_DATE || cellInfo.getCellType() == CellValueType.SHORT_DATE) {
            String format = cellInfo.getCellType() == CellValueType.FULL_DATE ? "dd/MM/yyyy HH:mm:ss" : "dd/MM/yyyy";
            java.util.Date date = Util.stringToDate(value, format);
            if(date == null) {
                throw new BusinessException("Không phải kiểu ngày tháng");
            }
            if(cellInfo.getCellType() == CellValueType.SHORT_DATE) {
                value += " 00:00:00";
            }
        } else {
            try{
                Class cls[] = new Class[] { String.class };
                cellInfo.getClassType().getConstructor(cls).newInstance(value);
            } catch (Exception e) {
                throw new BusinessException("Kiểu dữ liệu không đúng");
            }
        }
    }

    private static String getCellValue(Cell cell, FormulaEvaluator evaluator, CellInfo cellInfo) {
        DataFormatter dataFormatter = new DataFormatter();
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        if (cell.getDateCellValue() != null) {
                            String formatted = cellInfo.getCellType() == CellValueType.FULL_DATE ? "dd/MM/yyyy HH:mm:ss" : "dd/MM/yyyy";
                            SimpleDateFormat sdf = new SimpleDateFormat(formatted);
                            value = sdf.format(cell.getDateCellValue());
                        }
                    } else {
                        value = df.format(cell.getNumericCellValue());
                    }
                    break;
                case FORMULA:
                    return dataFormatter.formatCellValue(cell, evaluator).trim();
                default:
                    value = cell.getStringCellValue().trim();
            }
        }
        if(cellInfo.getCellType() == CellValueType.KEY_VALUE && value != null) {
            value = value.split("-")[0].trim();
        }
        return value;
    }
}
