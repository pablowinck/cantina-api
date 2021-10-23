package com.pablo.cantina.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import com.pablo.cantina.controller.form.AberturaCaixaForm;
import com.pablo.cantina.model.Caixa;
import com.pablo.cantina.service.CaixaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/caixa")
public class CaixaController {

    @Autowired
    private CaixaService caixaService;

    @GetMapping("/{id}")
    public ResponseEntity<Caixa> procurarCaixa(@PathVariable Long id) {
        return ResponseEntity.ok(caixaService.procurar(id));
    }

    @PostMapping("/abrir")
    @CacheEvict(value = { "listaCaixa", "listaCaixaAtivo" }, allEntries = true)
    public ResponseEntity<Caixa> abrirCaixa(@RequestBody @Valid AberturaCaixaForm caixa,
            UriComponentsBuilder uriBuilder) {
        return ResponseEntity.ok(caixaService.abrir(caixa));
    }

    @GetMapping("/fechar")
    @CacheEvict(value = { "listaCaixa", "listaCaixaAtivo" }, allEntries = true)
    public ResponseEntity<Caixa> fecharCaixa(@RequestParam Long id, @RequestParam BigDecimal valor) {
        return ResponseEntity.ok(caixaService.fechar(id, valor));
    }

    @GetMapping("/suprir")
    @CacheEvict(value = { "listaCaixa", "listaCaixaAtivo" }, allEntries = true)
    public ResponseEntity<Caixa> suprimento(@RequestParam Long id, @RequestParam String valor) {
        return ResponseEntity.ok(caixaService.suprir(id, new BigDecimal(valor)));
    }

    @GetMapping("/sangria")
    @CacheEvict(value = { "listaCaixa", "listaCaixaAtivo" }, allEntries = true)
    public ResponseEntity<Caixa> sangria(@RequestParam Long id, @RequestParam BigDecimal valor) {
        return ResponseEntity.ok(caixaService.sangria(id, valor));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = { "listaCaixa", "listaCaixaAtivo" }, allEntries = true)
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        caixaService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Cacheable(value = "listaCaixa")
    public Page<Caixa> listarTodos(Pageable pageable) {
        return caixaService.listarTodos(pageable);
    }

    @GetMapping("/ativos")
    @Cacheable(value = "listaCaixaAtivo")
    public Page<Caixa> listar(Pageable pageable) {
        return caixaService.listarAtivos(pageable);
    }

}
