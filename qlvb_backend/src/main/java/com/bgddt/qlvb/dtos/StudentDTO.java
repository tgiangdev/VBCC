package com.bgddt.qlvb.dtos;

import com.bgddt.qlvb.entities.StudentList;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Schema(name = "StudentDTO")
@Data
public class StudentDTO {
    private Long id;
    private Long studentIndex;

    @NotBlank(message = "Mã học sinh là bắt buộc")
    private String code;

    @NotBlank(message = "Họ học sinh là bắt buộc")
    private String firstName;

    @NotBlank(message = "Tên học sinh là bắt buộc")
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

    private Long studentListId;
}
