package com.pablo.cantina.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    private Caixa caixa;

    public boolean addProduto(ProdutoEstoque produto){
        if (this.produtos == null){
            this.produtos = new ArrayList<>();
        }
        return this.produtos.add(produto);
    }

    public boolean removeProduto(ProdutoEstoque produto){
        return this.produtos.remove(produto);
    }

    public BigDecimal getValorTotal() {
        return BigDecimal.valueOf(produtos.stream()
                .mapToDouble(produto ->
                        produto.getProduto()
                                .getValor_venda()
                                .doubleValue()).sum()
        );
    }
}
