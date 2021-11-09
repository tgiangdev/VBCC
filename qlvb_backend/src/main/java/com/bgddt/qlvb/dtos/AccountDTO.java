package com.bgddt.qlvb.dtos;

import com.bgddt.qlvb.common.enums.Role;
import com.bgddt.qlvb.entities.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class AccountDTO extends AbstractAuditingEntity{
        private Long id;

        @Max(45)
        private String email;

        @NotBlank(message = "Tài khoản là bắt buộc")
        @Max(45)
        private String username;

        @Max(64)
        @JsonIgnore
        private String password;

        @Max(50)
        private String name;

        //    @ElementCollection(targetClass = Role.class)
        @NotNull(message = "Chức vụ là bắt buộc")
        @Enumerated(EnumType.STRING)
        private Role role;

        private String type = "DTO";
}
