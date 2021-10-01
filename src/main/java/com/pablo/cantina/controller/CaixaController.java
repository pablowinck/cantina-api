package com.pablo.cantina.controller;

import com.pablo.cantina.controller.form.AberturaCaixaForm;
import com.pablo.cantina.model.Caixa;
import com.pablo.cantina.service.CaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/caixa")
public class CaixaController {

    @Autowired
    private CaixaService caixaService;

    @GetMapping("/{id}")
    public ResponseEntity<Caixa> procurarCaixa(@PathVariable Long id) {
        return caixaService.procurar(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @CacheEvict(value = {"listaCaixa", "listaCaixaAtivo"}, allEntries = true)
    public ResponseEntity<AberturaCaixaForm> abrirCaixa(@RequestBody @Valid AberturaCaixaForm caixa, UriComponentsBuilder uriBuilder) {
        URI uri = uriBuilder.path("/caixa/{id}").buildAndExpand(caixaService.abrir(caixa.converter())).toUri();
        return ResponseEntity.created(uri).body(caixa);
    }

    @GetMapping("/fechar")
    @CacheEvict(value = {"listaCaixa", "listaCaixaAtivo"}, allEntries = true)
    public ResponseEntity<Caixa> fecharCaixa(@RequestParam Long id, @RequestParam BigDecimal valor){
        return ResponseEntity.ok(caixaService.fechar(id, valor));
    }

    @GetMapping("/suprir")
    @CacheEvict(value = {"listaCaixa", "listaCaixaAtivo"}, allEntries = true)
    public ResponseEntity<Caixa> suprimento(@RequestParam Long id, @RequestParam BigDecimal valor){
        return ResponseEntity.ok(caixaService.suprir(id, valor));
    }

    @GetMapping("/sangria")
    @CacheEvict(value = {"listaCaixa", "listaCaixaAtivo"}, allEntries = true)
    public ResponseEntity<Caixa> sangria(@RequestParam Long id, @RequestParam BigDecimal valor){
        return ResponseEntity.ok(caixaService.sangria(id, valor));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = {"listaCaixa", "listaCaixaAtivo"}, allEntries = true)
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        caixaService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Cacheable(value = "listaCaixa")
    public List<Caixa> listarTodos() {
        return caixaService.listarTodos();
    }

    @GetMapping("/ativos")
    @Cacheable(value = "listaCaixaAtivo")
    public List<Caixa> listar() {
        return caixaService.listarAtivos();
    }

}
