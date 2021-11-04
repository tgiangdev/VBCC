package com.bgddt.qlvb.entities;


import com.bgddt.qlvb.common.enums.Ranking;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(columnDefinition = "integer default 2")
    private int gender;

    @Column
    private Date dateOfBirth;

    @Column
    private Ranking ranking;

    @Column(nullable = false)
    private Long clazzId;

    @Column
    private String description;
}
