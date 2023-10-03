package io.gihub.nivarna.application.service;

import io.gihub.nivarna.application.dto.MembroDTO;
import io.gihub.nivarna.domain.model.Membro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MembroService {

    Page<Membro> listarMembros(String nome, Pageable pageable);
    Membro detalharMembro(Long id);
    Membro cadastrarMembro(MembroDTO dto);
    Membro atualizarMembro(Long id, MembroDTO dto);
    void removerMembro(Long id);

}
