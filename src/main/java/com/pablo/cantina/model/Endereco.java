package com.pablo.cantina.model;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @Length(min = 1, max = 255, message = "O campo logradouro deve ter entre 1 e 255 caracteres")
    private String logradouro;
    @Length(min = 1, max = 6, message = "O campo numero deve ter entre 1 e 6 caracteres")
    private String numero;
    @Length(min = 1, max = 100, message = "O campo bairro deve ter entre 1 e 100 caracteres")
    private String bairro;
    @Length(min = 1, max = 120, message = "O campo cidade deve ter entre 1 e 120 caracteres")
    private String cidade;
    @Length(min = 8, max = 9, message = "O campo cep deve ter 8 caracteres")
    private String cep;
}
