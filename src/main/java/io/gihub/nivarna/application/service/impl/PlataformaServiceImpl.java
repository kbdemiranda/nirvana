package io.gihub.nivarna.application.service.impl;

import io.gihub.nivarna.Infrastructure.exception.NirvanaException;
import io.gihub.nivarna.Infrastructure.jpa.PlataformaRepository;
import io.gihub.nivarna.application.dto.PlataformaDTO;
import io.gihub.nivarna.application.service.PlataformaService;
import io.gihub.nivarna.domain.model.Plataforma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PlataformaServiceImpl implements PlataformaService {

    private final PlataformaRepository plataformaRepository;

    public PlataformaServiceImpl(PlataformaRepository plataformaRepository) {
        this.plataformaRepository = plataformaRepository;
    }

    @Override
    public Page<Plataforma> listarPlataformas(String nome, Pageable pageable) {
        return plataformaRepository.findPlataforma(nome, pageable);
    }

    @Override
    public Plataforma detalharPlataforma(Long id) {
        return getPlataforma(id);
    }

    @Override
    public Plataforma cadastrarPlataforma(PlataformaDTO dto) {
        Plataforma plataforma = Plataforma.builder()
                .nome(dto.getNome())
                .preco(dto.getPreco())
                .tipo(dto.getTipo())
                .totalVagas(dto.getTotalVagas())
                .vagasDisponiveis(dto.getTotalVagas()) //No cadastro de um serviço assume-se que todas as vagas estão disponíveis
                .dataCriacao(LocalDateTime.now())
                .build();

        return plataformaRepository.save(plataforma);
    }

    @Override
    public Plataforma atualizarPlataforma(Long id, PlataformaDTO dto) {
        Plataforma plataforma = getPlataforma(id);

        plataforma.setNome(dto.getNome());
        plataforma.setPreco(dto.getPreco());
        plataforma.setTipo(dto.getTipo());
        plataforma.setTotalVagas(dto.getTotalVagas());
        plataforma.setVagasDisponiveis(dto.getTotalVagas());
        plataforma.setDataAtualizacao(LocalDateTime.now());

        return plataformaRepository.save(plataforma);
    }

    @Override
    public void removerPlataforma(Long id) {
        Plataforma plataforma = getPlataforma(id);
        plataformaRepository.deletePlataforma(plataforma.getId());
    }

    private Plataforma getPlataforma(Long id){
        return plataformaRepository.findById(id).orElseThrow(() -> new NirvanaException(404, "Plataforma não encontrada"));
    }
}
