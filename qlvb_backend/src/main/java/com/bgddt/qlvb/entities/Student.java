package com.bgddt.qlvb.entities;


import com.bgddt.qlvb.common.enums.Ranking;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "student")
@Data
public class Student extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Mã học sinh là bắt buộc")
    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @NotBlank(message = "Tên học sinh là bắt buộc")
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(columnDefinition = "integer default 2")
    private int gender;

    @Column
    private Date dateOfBirth;

    @Column
    private Ranking ranking;

//    @NotBlank(message = "Lớp là bắt buộc")
//    @Column(nullable = false)
//    private Long clazzId;

    @Column
    private String description;

    @NotBlank(message = "Lớp là bắt buộc")
    @ManyToOne
    @JoinColumn(name = "class_id")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Clazz clazz;
}
