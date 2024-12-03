package com.template.backendtemplate.core.auth.entity;

import com.template.backendtemplate.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private  String name;
    @Column(nullable = false)
    private  String surname;
    @Column(nullable = false)
    private LocalDate birthdate;
    private String gender;
}