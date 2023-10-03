package io.gihub.nivarna.application.service.impl;

import io.gihub.nivarna.Infrastructure.jpa.PlataformaRepository;
import io.gihub.nivarna.application.dto.PlataformaDTO;
import io.gihub.nivarna.domain.model.Plataforma;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.gihub.nivarna.domain.enuns.TipoServico.STREAMING_VIDEO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class PlataformaServiceImplTest {

    @InjectMocks
    private PlataformaServiceImpl plataformaService;

    @Mock
    private PlataformaRepository plataformaRepository;
    
    @Captor
    private ArgumentCaptor<Plataforma> plataformaArgumentCaptor;

    @BeforeEach
    void setUp() {
    }

    @Test
    void listarPlataformas() {
        // Arrange / Given
        Pageable pageable = PageRequest.of(0, 10);
        List<Plataforma> plataformas = Arrays.asList(new Plataforma(), new Plataforma(), new Plataforma());
        Page<Plataforma> expectedPage = new PageImpl<>(plataformas, pageable, plataformas.size());

        // Act
        when(plataformaRepository.findPlataforma("", pageable)).thenReturn(expectedPage);

        // Assert
        Page<Plataforma> actualPage = plataformaService.listarPlataformas("", pageable);
        assertEquals(expectedPage, actualPage);
    }


    @Test
    void detalharPlataforma() {
        Long id = 1L;
        Plataforma expectedPlataforma = Plataforma.builder()
                .id(id)
                .nome("Netflix")
                .preco(BigDecimal.valueOf(29.90))
                .tipo(STREAMING_VIDEO)
                .totalVagas(5)
                .dataCriacao(LocalDateTime.now())
                .build();

        // Configure mock
        when(plataformaRepository.findById(id)).thenReturn(Optional.of(expectedPlataforma));

        // Act
        Plataforma resultado = plataformaService.detalharPlataforma(id);

        // Assert
        assertNotNull(resultado);
    }

    @Test
    void deveCadastrarPlataforma() {
        // Arrange
        PlataformaDTO dto = new PlataformaDTO();
        dto.setNome("Netflix");
        dto.setPreco(BigDecimal.valueOf(29.90));
        dto.setTipo(STREAMING_VIDEO);
        dto.setTotalVagas(5);
        dto.setDataCriacao(LocalDateTime.now());

        Plataforma expectedPlataforma = Plataforma.builder()
                .nome("Netflix")
                .preco(BigDecimal.valueOf(29.90))
                .tipo(STREAMING_VIDEO)
                .totalVagas(5)
                .dataCriacao(LocalDateTime.now())
                .build();

        // Act
        plataformaService.cadastrarPlataforma(dto);

        // Assert
        then(plataformaRepository).should().save(plataformaArgumentCaptor.capture());

        Plataforma capturedPlataforma = plataformaArgumentCaptor.getValue();
        assertEquals(expectedPlataforma, capturedPlataforma);
    }



    @Test
    void atualizarPlataforma() {
        // Arrange
        PlataformaDTO dto = new PlataformaDTO();
        dto.setNome("Netflix Atualizado");
        dto.setPreco(BigDecimal.valueOf(35.90));
        dto.setTipo(STREAMING_VIDEO);
        dto.setTotalVagas(6);

        Plataforma existingPlataforma = Plataforma.builder()
                .id(1L)
                .nome("Netflix")
                .preco(BigDecimal.valueOf(29.90))
                .tipo(STREAMING_VIDEO)
                .totalVagas(5)
                .build();

        Plataforma updatedPlataforma = Plataforma.builder()
                .id(1L)
                .nome("Netflix Atualizado")
                .preco(BigDecimal.valueOf(35.90))
                .tipo(STREAMING_VIDEO)
                .totalVagas(6)
                .build();

        when(plataformaRepository.findById(1L)).thenReturn(Optional.of(existingPlataforma));

        // Act
        plataformaService.atualizarPlataforma(1L, dto);

        // Assert
        then(plataformaRepository).should().save(plataformaArgumentCaptor.capture());
        Plataforma capturedPlataforma = plataformaArgumentCaptor.getValue();
        assertEquals(updatedPlataforma, capturedPlataforma);
    }


    @Test
    void removerPlataforma() {
        // Arrange
        Long idParaRemover = 1L;

        Plataforma plataformaExistente = Plataforma.builder()
                .id(idParaRemover)
                .nome("Netflix")
                .preco(BigDecimal.valueOf(29.90))
                .tipo(STREAMING_VIDEO)
                .totalVagas(5)
                .build();

        when(plataformaRepository.findById(idParaRemover)).thenReturn(Optional.of(plataformaExistente));

        // Act
        plataformaService.removerPlataforma(idParaRemover);

        // Assert
        then(plataformaRepository).should().deletePlataforma(idParaRemover);
    }

}