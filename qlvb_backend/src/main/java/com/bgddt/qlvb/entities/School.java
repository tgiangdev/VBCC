package com.bgddt.qlvb.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "school")
@Data
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 50)
    private String name;

    @Column
    private String address;

    @Column
    private String description;
}
