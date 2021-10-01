package com.pablo.cantina;

import com.pablo.cantina.model.Carrinho;
import com.pablo.cantina.model.Produto;
import com.pablo.cantina.model.ProdutoEstoque;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootTest
class CantinaApplicationTests {
    @Test
    void testaSomaDeValorTotalDoCarrinho(){
        Carrinho carrinho = new Carrinho();

        Produto produto = new Produto();
        produto.setDescricao("Água com gás");
        produto.setValor_venda(new BigDecimal("4"));

        ProdutoEstoque produtoEstoque = new ProdutoEstoque();
        produtoEstoque.setProduto(produto);
        produtoEstoque.setQnt(2);

        carrinho.setProdutos(Arrays.asList(
                produtoEstoque,
                produtoEstoque
        ));

        Assertions.assertEquals(carrinho.getValorTotal(), new BigDecimal("8.0"));
    }
}
