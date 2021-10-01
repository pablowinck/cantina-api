package com.pablo.cantina.service;

import com.pablo.cantina.model.Caixa;
import com.pablo.cantina.repository.CaixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CaixaService {

    @Autowired
    private CaixaRepository caixaRepository;

    public Optional<Caixa> procurar(Long id) {
        return null;
    }

    public Caixa abrir(Caixa caixa) {
        return null;
    }

    public Caixa fechar(Long id, BigDecimal valor) {
        return null;
    }

    public Caixa suprir(Long id, BigDecimal valor) {
        return null;
    }

    public Caixa sangria(Long id, BigDecimal valor) {
        return null;
    }

    public void deletar(Long id) {

    }

    public List<Caixa> listarTodos() {
        return new ArrayList<>();
    }

    public List<Caixa> listarAtivos() {
        return new ArrayList<>();
    }
}
