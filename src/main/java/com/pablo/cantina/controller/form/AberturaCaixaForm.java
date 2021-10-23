package com.pablo.cantina.controller.form;

import com.pablo.cantina.enums.TipoCaixa;
import com.pablo.cantina.model.Caixa;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class AberturaCaixaForm {
    @Length(min = 5, max = 250, message = "Descrição tem que ter de 5 a 250 caracteres.")
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoCaixa tipo;

    @Digits(integer = 7, fraction = 2, message = "Formatação incorreta, ex: 10000.00")
    private BigDecimal valor;

    public Caixa converter() {
        Caixa caixa = new Caixa();
        caixa.setTipo(tipo);
        caixa.setValor_abertura(valor);
        caixa.setValor(valor);
        caixa.setData_abertura(LocalDateTime.now());
        caixa.setDescricao(descricao);
        return caixa;
    }
}
