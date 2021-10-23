package com.pablo.cantina.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carrinho {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<ProdutoEstoque> produtos;

    @OneToOne
    @NotNull(message = "Caixa é obrigatório")
    private Caixa caixa;

    public boolean addProduto(ProdutoEstoque produto) {
        if (this.produtos == null) {
            this.produtos = new ArrayList<>();
        }
        return this.produtos.add(produto);
    }

    public boolean removeProduto(ProdutoEstoque produto) {
        return this.produtos.remove(produto);
    }

    public BigDecimal getValorTotal() {
        return BigDecimal.valueOf(
                produtos.stream().mapToDouble(produto -> produto.getProduto().getValor_venda().doubleValue()).sum());
    }
}
