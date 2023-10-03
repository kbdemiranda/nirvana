package io.gihub.nivarna.application.controller;

import io.gihub.nivarna.application.dto.MembroDTO;
import io.gihub.nivarna.application.service.MembroService;
import io.gihub.nivarna.domain.model.Membro;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/membros")
public class MembroController {

    private final MembroService membroService;

    public MembroController(MembroService membroService) {
        this.membroService = membroService;
    }

    @GetMapping
    public ResponseEntity<?> listarMembros(@RequestParam(required = false) String nome, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(membroService.listarMembros(nome, PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalharMebro(@PathVariable Long id) {
        return ResponseEntity.ok(membroService.detalharMembro(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarMembro(@RequestBody MembroDTO dto){
        Membro membro = membroService.cadastrarMembro(dto);
        return ResponseEntity.created(URI.create("/membros/" + membro.getId())).body(membro);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizarMembro(@PathVariable Long id, @RequestBody MembroDTO dto){
        Membro membro = membroService.atualizarMembro(id, dto);
        return ResponseEntity.created(URI.create("/membros/" + membro.getId())).body(membro);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarMembro(@PathVariable Long id){
        membroService.removerMembro(id);
        return ResponseEntity.noContent().build();
    }
}
