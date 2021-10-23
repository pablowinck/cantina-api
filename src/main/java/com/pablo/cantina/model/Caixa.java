package com.pablo.cantina.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pablo.cantina.enums.TipoCaixa;

import org.hibernate.Hibernate;
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
public class Caixa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(min = 5, max = 250, message = "Descrição tem que ter de 5 a 250 caracteres.")
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoCaixa tipo;

    @Digits(integer = 7, fraction = 2, message = "Formatação incorreta, ex: 10000.00")
    private BigDecimal valor, valor_abertura, valor_fechamento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDateTime data_abertura, data_fechamento;

    public boolean isAtivo() {
        return data_fechamento == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Caixa caixa = (Caixa) o;
        return Objects.equals(id, caixa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
