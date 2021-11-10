package com.bgddt.qlvb.services.excel;

import java.util.Arrays;

public class RowInfo {
    public static CellInfo[] DEFINE_COLUMNS_EXCEL = {
            CellInfo.key("firstName").text(32),
            CellInfo.key("lastName").text(32),
            CellInfo.key("gender").keyValue(1, Arrays.asList("0", "1")),
            CellInfo.key("dateOfBirth").fullDate(),
            CellInfo.key("nationId").keyValue(2),
            CellInfo.key("trainingTypeId").keyValue(1),
            CellInfo.key("graduationGradingId").keyValue(1),
            CellInfo.key("points").doubleNumber()
    };
}
