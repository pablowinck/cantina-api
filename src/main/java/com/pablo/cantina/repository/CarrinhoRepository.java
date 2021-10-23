package com.pablo.cantina.repository;

import com.pablo.cantina.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{
    
}
