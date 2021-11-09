package com.bgddt.qlvb.dtos;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
public class SchoolDTO {
    private Long id;

    @NotBlank(message = "Mã trường là bắt buộc")
    @Max(50)
    private String code;

    @NotBlank(message = "Tên trường là bắt buộc")
    @Max(50)
    private String name;

    @Column
    private String address;

    @Column
    private String description;
}
