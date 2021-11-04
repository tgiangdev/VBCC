package com.bgddt.qlvb.services.impl;

import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.entities.Student;
import com.bgddt.qlvb.models.ImportRequest;
import com.bgddt.qlvb.repositories.StudentRepository;
import com.bgddt.qlvb.services.StudentService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository bookRepository) {
        this.studentRepository = bookRepository;
    }


    @Override
    public List<Student> importExcel(MultipartFile file, ImportRequest importRequest) throws IOException, BusinessException {
        int sheetIndex = 0, rowIndex = 3;
//        InputStream fileIS = new BufferedInputStream(file.getInputStream());
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        for(Row row: sheet) {
            if (row.getRowNum() < rowIndex) continue;
            for (Cell cell : row) {
                System.out.println(getCellValue(cell, String.class).toString());
            }
        }
        return new ArrayList<>();
    }

    private <T> T getCellValue(Cell cell, Class needClass) throws BusinessException {
        try {
            String value = "";
            switch (cell.getCellType()) {
                case NUMERIC:
                    value = String.valueOf(cell.getNumericCellValue());
                    break;
                case BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case FORMULA:
                    value = String.valueOf(cell.getCellFormula());
                    break;
                case STRING:
                default:
                    value = cell.getStringCellValue();
                    break;
            }

            Class cls[] = new Class[] { String.class };
            return (T) needClass.getConstructor(cls).newInstance(value);
//            return value;
        } catch (Exception e) {
            throw new BusinessException(String.format("Lỗi đọc excel dòng %s, cột %s", cell.getRowIndex(), cell.getColumnIndex()), e.getMessage());
        }
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findAllByClazz(Long clazzId) throws BusinessException {
        //throw new BusinessException("Lỗi", "Mô tả");
        return studentRepository.findAllByClazzId(clazzId);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public boolean deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return true;
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        return studentRepository.save(student);
    }
}
