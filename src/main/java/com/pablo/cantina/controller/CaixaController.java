package com.pablo.cantina.controller;

import com.pablo.cantina.model.Caixa;
import com.pablo.cantina.repository.CaixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/caixa")
public class CaixaController {

    @Autowired
    private CaixaRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Caixa> pesquisar(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaCaixa", allEntries = true)
    public ResponseEntity<Caixa> salvar(@RequestBody @Valid Caixa caixa, UriComponentsBuilder uriBuilder) {
        if (caixa.getId() != null) {
            Optional<Caixa> optional = repository.findById(caixa.getId());
            if (optional.isPresent()) {
                return ResponseEntity.ok(repository.save(caixa));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            // cadastrar
            this.repository.save(caixa);
            URI uri = uriBuilder.path("/caixa/{id}").buildAndExpand(caixa.getId()).toUri();
            return ResponseEntity.created(uri).body(caixa);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaCaixa", allEntries = true)
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Caixa> optional = repository.findById(id);
        if (optional.isPresent()) {
            this.repository.deleteById(id);
            return ResponseEntity.ok().build(); // retorna 200 -> conseguiu excluir
        }
        return ResponseEntity.notFound().build(); // retorna 404 -> não achou
    }

    @GetMapping
    @Cacheable(value = "listaCaixa")
    public List<Caixa> listar() {
        return this.repository.findAll();
    }

}
