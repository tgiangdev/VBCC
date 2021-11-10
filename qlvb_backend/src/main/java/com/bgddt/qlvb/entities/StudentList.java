package com.bgddt.qlvb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student_list")
@Data
public class StudentList extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Năm học là bắt buộc")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_year_id")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private SchoolYear schoolYear;

    @NotNull(message = "Trường là bắt buộc")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private School school;

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

    @JsonIgnore
    @OneToMany(mappedBy = "studentList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Không sử dụng trong toString()
    private List<Student> students;
}
