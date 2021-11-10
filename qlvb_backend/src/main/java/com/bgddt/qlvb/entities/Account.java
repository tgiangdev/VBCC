package com.bgddt.qlvb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.bgddt.qlvb.common.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    @Column(length = 45)
    private String email;

    @NotBlank(message = "Tài khoản là bắt buộc")
    @Column(nullable = false, unique = true, length = 45)
    private String username;

    @Column(nullable = false, length = 64)
    @JsonIgnore
    private String password;

    @Column(length = 50, columnDefinition = "nvarchar(64)")
    private String name;



//    @ElementCollection(targetClass = Role.class)
    @NotNull(message = "Chức vụ là bắt buộc")
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

//    @NotNull(message = "Danh sách là bắt buộc")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Không sử dụng trong toString()
    private StudentList studentList;
}
