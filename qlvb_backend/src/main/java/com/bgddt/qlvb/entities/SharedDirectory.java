package com.bgddt.qlvb.entities;

import lombok.Data;

import javax.persistence.*;

// Danh mục dùng chung
@Entity
@Table(name = "shared_directory")
@Data
public class SharedDirectory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;
}
