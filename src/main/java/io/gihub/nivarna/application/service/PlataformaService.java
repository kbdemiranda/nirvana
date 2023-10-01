package io.gihub.nivarna.application.service;

import io.gihub.nivarna.application.dto.PlataformaDTO;
import io.gihub.nivarna.domain.model.Plataforma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlataformaService {

    Page<Plataforma> listarPlataformas(String nome, Pageable pageable);

    Plataforma detalharPlataforma(Long id);

    Plataforma cadastrarPlataforma(PlataformaDTO dto);

    Plataforma atualizarPlataforma(Long id, PlataformaDTO dto);

    void removerPlataforma(Long id);


}
