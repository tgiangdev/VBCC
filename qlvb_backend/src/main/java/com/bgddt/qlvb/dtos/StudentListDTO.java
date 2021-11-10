package com.bgddt.qlvb.dtos;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class StudentListDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Năm học là bắt buộc")
    private Long schoolYearId;

    @NotNull(message = "Trường là bắt buộc")
    private Long schoolId;

    @NotNull(message = "Trường là bắt buộc")
    private String schoolName;

    @NotEmpty(message = "Số quyết định tốt nghiệp không được bỏ trống")
    @Column
    private String decisionNumber;

    @NotNull(message = "Ngày quyết định tốt nghiệp không được bỏ trống")
    @Column
    private Date dateOfDecision;

    @NotEmpty(message = "Tên kỳ quyết định không được bỏ trống")
    @Column
    private String graduationExam;

    @NotEmpty(message = "Tên khoá quyết định không được bỏ trống")
    @Column
    private String graduationCourse;

    @Column
    private String status;

    @Column
    private Date publishedOn;

    @Column
    private Date approvedOn;

    @Column
    private String approvedFeedback;

    private List<StudentDTO> students;
}
