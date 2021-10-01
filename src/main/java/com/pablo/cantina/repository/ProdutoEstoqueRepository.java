package com.pablo.cantina.repository;

import com.pablo.cantina.model.ProdutoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque, Long>{
    
}
