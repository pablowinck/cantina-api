package com.pablo.cantina.repository;

import com.pablo.cantina.model.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaixaRepository extends JpaRepository<Caixa, Long>{
    
}
