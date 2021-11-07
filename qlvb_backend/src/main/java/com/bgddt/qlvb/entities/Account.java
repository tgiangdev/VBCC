package com.bgddt.qlvb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.bgddt.qlvb.common.enums.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "account")
@Data
public class Account extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 45)
    private String email;

    @NotBlank(message = "Tài khoản là bắt buộc")
    @Column(nullable = false, unique = true, length = 45)
    private String username;

    @Column(nullable = false, length = 64)
    @JsonIgnore
    private String password;

    @Column(length = 50)
    private String name;

//    @ElementCollection(targetClass = Role.class)
    @NotBlank(message = "Chức vụ là bắt buộc")
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

}
