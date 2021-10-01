package com.pablo.cantina.repository;

import com.pablo.cantina.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long>{
    
}
