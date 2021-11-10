package com.bgddt.qlvb.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "school_year")
@Data
public class SchoolYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Mã năm học là bắt buộc")
    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @NotNull(message = "Ngày bắt đầu năm học là bắt buộc")
    @Column(nullable = false)
    private Date startSubmissionOn;

    @NotNull(message = "Ngày bắt đầu năm học là bắt buộc")
    @Column(nullable = false)
    private Date endSubmissionOn;
}
