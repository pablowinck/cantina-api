package com.pablo.cantina.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(min = 3, max = 250, message = "O campo descrição deve ter entre 3 e 250 caracteres")
    @NotNull(message = "O campo descrição é obrigatório")
    private String descricao;

    @Digits(integer = 7, fraction = 2, message = "Formatação incorreta, ex: 10000.00")
    @NotNull(message = "O campo valor de custo é obrigatório")
    private BigDecimal valor_custo;

    @Digits(integer = 7, fraction = 2, message = "Formatação incorreta, ex: 10000.00")
    @NotNull(message = "O campo valor de venda é obrigatório")
    private BigDecimal valor_venda;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDateTime data_cadastro = LocalDateTime.now();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Produto produto = (Produto) o;
        return id.equals(produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
