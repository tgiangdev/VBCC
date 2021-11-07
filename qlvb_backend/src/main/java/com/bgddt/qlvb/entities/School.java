package com.bgddt.qlvb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "school")
@Data
public class School extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Mã trường là bắt buộc")
    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @NotBlank(message = "Tên trường là bắt buộc")
    @Column(nullable = false, length = 50)
    private String name;

    @Column
    private String address;

    @Column
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<Clazz> clazzList;
}
