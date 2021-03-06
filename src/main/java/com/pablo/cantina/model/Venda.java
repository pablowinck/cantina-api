package com.pablo.cantina.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Digits(integer = 7, fraction = 2, message = "Formatação incorreta, ex: 10000.00")
    @NotNull(message = "Valor dos produtos é obrigatório")
    private BigDecimal valor_produtos;

    @Digits(integer = 7, fraction = 2, message = "Formatação incorreta, ex: 10000.00")
    private BigDecimal valor_desconto;

    @Digits(integer = 7, fraction = 2, message = "Formatação incorreta, ex: 10000.00")
    @NotNull(message = "Valor da venda é obrigatório")
    private BigDecimal valor_total;

    @OneToOne
    @JoinColumn(name = "carrinho_id")
    @NotNull(message = "Carrinho é obrigatório")
    private Carrinho carrinho;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDateTime data_cadastro;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Venda venda = (Venda) o;
        return id.equals(venda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
