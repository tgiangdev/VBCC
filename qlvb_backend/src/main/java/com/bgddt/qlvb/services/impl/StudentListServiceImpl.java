package com.bgddt.qlvb.services.impl;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.dtos.AccountDTO;
import com.bgddt.qlvb.dtos.StudentDTO;
import com.bgddt.qlvb.dtos.StudentListDTO;
import com.bgddt.qlvb.entities.Student;
import com.bgddt.qlvb.entities.StudentList;
import com.bgddt.qlvb.models.ImportRequest;
import com.bgddt.qlvb.repositories.StudentListRepository;
import com.bgddt.qlvb.services.AccountService;
import com.bgddt.qlvb.services.BaseService;
import com.bgddt.qlvb.services.StudentListService;
import com.bgddt.qlvb.services.StudentService;
import com.bgddt.qlvb.services.excel.CellInfo;
import com.bgddt.qlvb.services.excel.CellValueType;
import com.bgddt.qlvb.services.excel.RowInfo;
import com.bgddt.qlvb.utils.ExcelUtil;
import com.google.gson.Gson;
import net.minidev.json.JSONObject;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentListServiceImpl extends BaseServiceImpl<StudentListDTO, StudentList> implements StudentListService<StudentListDTO, StudentList> {
    StudentListRepository studentListRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    AccountService accountService;

    @Autowired
    protected StudentListServiceImpl(StudentListRepository repository) {
        super(repository, StudentListDTO.class, StudentList.class);
        this.studentListRepository = repository;
    }

    @Override
    @Transactional
    public StudentListDTO importExcel(MultipartFile file, StudentListDTO studentListDTO) throws IOException, BusinessException {
        int sheetIndex = 0, rowIndex = 1;
        // Validate Header
        AccountDTO accountDTO = accountService.getCurrentAccount();
        if(accountDTO.getSchoolId() == null) {
            throw new BusinessException("Tài khoản của bạn chưa được gắn với Trường học nào");
        }
        studentListDTO.setSchoolId(accountDTO.getSchoolId());

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for(Row row: sheet) {
            try {
                if (row.getRowNum() < rowIndex) continue;
                int cellIndex = 0;
                JSONObject jsonObject = new JSONObject();
                for (CellInfo cellInfo : RowInfo.DEFINE_COLUMNS_EXCEL) {
                    String cellValue = ExcelUtil.getCellValueAndValidate(row.getCell(cellIndex), evaluator, cellInfo);
                    jsonObject.put(cellInfo.getKey(), cellValue.isEmpty() && cellInfo.getCellType() != CellValueType.TEXT ? null : cellValue);
                    cellIndex++;
                }
                System.out.println(jsonObject.toJSONString());
                if(jsonObject.toJSONString().isEmpty()) {
                    continue;
                }
                StudentDTO studentDTO = GSON.fromJson(jsonObject.toJSONString(), StudentDTO.class);
                studentDTOS.add(studentDTO);
            } catch (BusinessException e) {
                throw e;
            } catch (Exception e) {
                throw new BusinessException(String.format("Lỗi đọc file dòng %s: %s",
                        row.getRowNum() + 2,
                        e.getMessage())
                );
            }
        }
        System.out.println("studentListDTO " + GSON.toJson(studentListDTO));
        System.out.println("studentDTOS " + GSON.toJson(studentDTOS));

        // Save to database
        StudentListDTO studentListDTOSaved = create(studentListDTO);

        for(int i = 0; i<studentDTOS.size();i++) {
            studentDTOS.get(i).setStudentIndex((long)i + 1);
            studentDTOS.get(i).setStudentListId(studentListDTOSaved.getId());
        }
        studentDTOS = studentService.createAll(studentDTOS);
        studentListDTO.setStudents(studentDTOS);
        return studentListDTO;
    }
}
