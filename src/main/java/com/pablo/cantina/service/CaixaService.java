package com.pablo.cantina.service;

import java.math.BigDecimal;

import com.pablo.cantina.controller.form.AberturaCaixaForm;
import com.pablo.cantina.error.ResourceNotFoundException;
import com.pablo.cantina.model.Caixa;
import com.pablo.cantina.repository.CaixaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CaixaService {

    @Autowired
    private CaixaRepository caixaRepository;

    public Caixa procurar(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id não pode ser nulo");

        return caixaRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Caixa não encontrado");
        });
    }

    public Caixa abrir(AberturaCaixaForm caixaNaoAberto) {
        Caixa caixa = caixaNaoAberto.converter();
        caixa.setValor(caixa.getValor_abertura());
        return caixaRepository.save(caixa);
    }

    public Caixa fechar(Long id, BigDecimal valor) {
        testValor(valor);
        Caixa caixa = procurar(id);

        if (caixa.getValor_fechamento() != null)
            throw new IllegalArgumentException("Caixa já foi fechado");
        if (caixa.getValor_abertura() == null)
            throw new IllegalArgumentException("Caixa não foi aberto");
        if (caixa.getValor().compareTo(valor) != 0)
            throw new IllegalArgumentException("Valor de fechamento diferente do valor que deveria constar no caixa");

        caixa.setValor_fechamento(valor);
        return caixaRepository.save(caixa);
    }

    public Caixa suprir(Long id, BigDecimal valor) {
        testValor(valor);
        Caixa caixa = procurar(id);

        if (caixa.getValor() == null)
            caixa.setValor(BigDecimal.ZERO);

        caixa.setValor(caixa.getValor().add(valor));
        return caixaRepository.save(caixa);
    }

    public Caixa sangria(Long id, BigDecimal valor) {
        testValor(valor);
        Caixa caixa = procurar(id);

        BigDecimal novoValor = caixa.getValor().subtract(valor);

        if (novoValor.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Resultado pós sangria não pode ser negativo");

        caixa.setValor(novoValor);
        return caixaRepository.save(caixa);
    }

    public void deletar(Long id) {
        Caixa caixa = procurar(id);
        caixaRepository.delete(caixa);
    }

    public Page<Caixa> listarTodos(Pageable pageable) {
        return caixaRepository.findAll(pageable);
    }

    public Page<Caixa> listarAtivos(Pageable pageable) {
        return caixaRepository.findByAtivo(true, pageable);
    }

    private void testValor(BigDecimal valor) {
        if (valor == null)
            throw new IllegalArgumentException("Valor não pode ser nulo");
        if (valor.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Valor não pode ser negativo");
    }

}
