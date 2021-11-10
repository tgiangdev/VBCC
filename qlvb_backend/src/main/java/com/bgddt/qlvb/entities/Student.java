package com.bgddt.qlvb.entities;


import com.bgddt.qlvb.common.enums.Ranking;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "student")
@Data
public class Student extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentIndex;

    @NotBlank(message = "Mã học sinh là bắt buộc")
    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @NotBlank(message = "Họ học sinh là bắt buộc")
    @Column(nullable = false, unique = true, length = 50, columnDefinition = "nvarchar(32)")
    private String firstName;

    @NotBlank(message = "Tên học sinh là bắt buộc")
    @Column(nullable = false, unique = true, length = 50, columnDefinition = "nvarchar(32)")
    private String lastName;

    @Column
    private String gender;

    @Column
    private Date dateOfBirth;

    @Column
    private String nationId;

    @Column
    private String trainingTypeId;

    @Column
    private Long graduationGradingId;

    @Column
    private Double points;

    @NotNull(message = "Danh sách là bắt buộc")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_list_id")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Không sử dụng trong toString()
    private StudentList studentList;
}
