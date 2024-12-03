package com.template.backendtemplate.core.auth.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDto {
    @NotBlank
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private  String name;
    @NotBlank
    private  String surname;
    @NotBlank
    private LocalDate birthdate;
    private String gender;
}