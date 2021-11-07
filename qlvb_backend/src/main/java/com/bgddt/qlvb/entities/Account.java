package com.bgddt.qlvb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.bgddt.qlvb.common.enums.Role;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "account")
@Data
public class Account extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 45)
    private String email;

    @Column(nullable = false, unique = true, length = 45)
    private String username;

    @Column(nullable = false, length = 64)
    @JsonIgnore
    private String password;

    @Column(length = 50)
    private String name;

//    @ElementCollection(targetClass = Role.class)
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

}
