package com.bgddt.qlvb.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "class")
@Data
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false)
    private Long schoolId;

    @Column
    private String description;
}
