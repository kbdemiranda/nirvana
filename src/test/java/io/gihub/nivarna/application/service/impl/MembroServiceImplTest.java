package io.gihub.nivarna.application.service.impl;

import io.gihub.nivarna.Infrastructure.jpa.MembroRepository;
import io.gihub.nivarna.application.dto.MembroDTO;
import io.gihub.nivarna.domain.model.Membro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MembroServiceImplTest {

    @InjectMocks
    private MembroServiceImpl membroService;

    @Mock
    private MembroRepository membroRepository;

    @Captor
    private ArgumentCaptor<Membro> membroArgumentCaptor;

    @BeforeEach
    void setUp() {
    }

    @Test
    void listarMembros() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        List<Membro> membros = Arrays.asList(new Membro(), new Membro(), new Membro());
        Page<Membro> expectedPage = new PageImpl<>(membros, pageable, membros.size());

        // Act
        when(membroRepository.findMembro("", pageable)).thenReturn(expectedPage);

        // Assert
        Page<Membro> actualPage = membroService.listarMembros("", pageable);
        assertEquals(expectedPage, actualPage);
    }

    @Test
    void detalharMembro() {
        // Arrange
        Long id = 1L;
        Membro expectedMembro = new Membro(id, "João", "joao@email.com", LocalDateTime.now(), null, null);

        // Act
        when(membroRepository.findById(id)).thenReturn(Optional.of(expectedMembro));

        // Assert
        Membro resultado = membroService.detalharMembro(id);
        assertNotNull(resultado);
    }

    @Test
    void deveCadastrarMembro() {
        // Arrange
        MembroDTO dto = new MembroDTO();
        dto.setNome("João");
        dto.setEmail("joao@email.com");

        // Act
        membroService.cadastrarMembro(dto);

        // Assert
        then(membroRepository).should().save(membroArgumentCaptor.capture());

        Membro capturedMembro = membroArgumentCaptor.getValue();
        assertEquals(dto.getNome(), capturedMembro.getNome());
        assertEquals(dto.getEmail(), capturedMembro.getEmail());
    }

    @Test
    void atualizarMembro() {
        // Arrange
        Long id = 1L;
        MembroDTO dto = new MembroDTO();
        dto.setNome("João Atualizado");
        dto.setEmail("joao.atualizado@email.com");

        Membro existingMembro = new Membro(id, "João", "joao@email.com", LocalDateTime.now(), null, null);

        // Act
        when(membroRepository.findById(id)).thenReturn(Optional.of(existingMembro));
        membroService.atualizarMembro(id, dto);

        // Assert
        then(membroRepository).should().save(membroArgumentCaptor.capture());

        Membro capturedMembro = membroArgumentCaptor.getValue();
        assertEquals(dto.getNome(), capturedMembro.getNome());
        assertEquals(dto.getEmail(), capturedMembro.getEmail());
    }

    @Test
    void removerMembro() {
        // Arrange
        Long id = 1L;
        Membro existingMembro = new Membro(id, "João", "joao@email.com", LocalDateTime.now(), null, null);

        // Act
        when(membroRepository.findById(id)).thenReturn(Optional.of(existingMembro));
        membroService.removerMembro(id);

        // Assert
        then(membroRepository).should().deleteMembro(id);
    }
}
