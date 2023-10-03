package io.gihub.nivarna.application.service.impl;

import io.gihub.nivarna.Infrastructure.exception.NirvanaException;
import io.gihub.nivarna.Infrastructure.jpa.MembroRepository;
import io.gihub.nivarna.application.dto.MembroDTO;
import io.gihub.nivarna.application.service.MembroService;
import io.gihub.nivarna.domain.model.Membro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MembroServiceImpl implements MembroService {


    private final MembroRepository membroRepository;

    public MembroServiceImpl(MembroRepository membroRepository) {
        this.membroRepository = membroRepository;
    }

    @Override
    public Page<Membro> listarMembros(String nome, Pageable pageable) {
        return membroRepository.findMembro(nome, pageable);
    }

    @Override
    public Membro detalharMembro(Long id) {
        return getMembro(id);
    }

    @Override
    public Membro cadastrarMembro(MembroDTO dto) {
        Membro membro = Membro.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .dataCadastro(LocalDateTime.now())
                .build();

        return membroRepository.save(membro);
    }

    @Override
    public Membro atualizarMembro(Long id, MembroDTO dto) {
        Membro membro = getMembro(id);
        membro.setNome(dto.getNome());
        membro.setEmail(dto.getEmail());
        membro.setDataAtualizacao(LocalDateTime.now());
        return membroRepository.save(membro);
    }

    @Override
    public void removerMembro(Long id) {
        Membro membro = getMembro(id);
        membroRepository.deleteMembro(membro.getId());
    }

    private Membro getMembro(Long id) {
        return membroRepository.findById(id).orElseThrow(() -> new NirvanaException(404, "Membro não encontrado"));
    }
}
