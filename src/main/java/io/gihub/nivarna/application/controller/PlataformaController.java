package io.gihub.nivarna.application.controller;

import io.gihub.nivarna.application.dto.PlataformaDTO;
import io.gihub.nivarna.application.service.PlataformaService;
import io.gihub.nivarna.domain.model.Plataforma;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/plataformas")
public class PlataformaController {

    private final PlataformaService plataformaService;

    public PlataformaController(PlataformaService plataformaService) {
        this.plataformaService = plataformaService;
    }

    @GetMapping
    public ResponseEntity<?> listarPlataformas(@RequestParam(required = false) String nome, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(plataformaService.listarPlataformas(nome, PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalharPlataforma(@PathVariable Long id) {
        return ResponseEntity.ok(plataformaService.detalharPlataforma(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarPlataforma(@RequestBody @Valid PlataformaDTO dto) {
        Plataforma plataforma = plataformaService.cadastrarPlataforma(dto);

        return ResponseEntity.created(URI.create("/plataformas/" + plataforma.getId())).body(plataforma);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizarPlataforma(@PathVariable Long id, @RequestBody @Valid PlataformaDTO dto) {
        Plataforma plataforma = plataformaService.atualizarPlataforma(id, dto);

        return ResponseEntity.created(URI.create("/plataformas/" + plataforma.getId())).body(plataforma);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removerPlataforma(@PathVariable Long id) {
        plataformaService.removerPlataforma(id);

        return ResponseEntity.noContent().build();
    }
}
