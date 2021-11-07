package com.bgddt.qlvb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "class")
@Data
public class Clazz extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên lớp là bắt buộc")
    @Column(nullable = false, unique = true, length = 50)
    private String name;

//    @NotBlank(message = "Trường là bắt buộc")
//    @Column(nullable = false)
//    private Long schoolId;

    @Column
    private String description;

    @NotBlank(message = "Trường là bắt buộc")
    @ManyToOne
    @JoinColumn(name = "school_id")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private School school;

    @JsonIgnore
    @OneToMany(mappedBy = "clazz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<Student> students;
}
