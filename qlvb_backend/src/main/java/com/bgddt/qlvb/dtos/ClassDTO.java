package com.bgddt.qlvb.dtos;

import com.bgddt.qlvb.entities.School;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ClassDTO {
    private Long id;

    @NotBlank(message = "Tên lớp là bắt buộc")
    @Max(50)
    private String name;

    @NotNull(message = "Trường là bắt buộc")
    private Long schoolId;

    @Column
    private String description;

    private String schoolName;
}
