package com.pablo.cantina.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contato {
    @NotBlank
    private String celular;
    @Email
    @NotBlank
    private String email;
}
