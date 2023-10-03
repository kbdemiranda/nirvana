package io.gihub.nivarna.application.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Data
public class MembroDTO {

    private Long id;
    @NotBlank(message = "Nome não pode ser vazio.")
    private String nome;
    @Email(message = "Email inválido.")
    @NotBlank(message = "Email não pode ser vazio.")
    private String email;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    private LocalDateTime dataExclusao;
}

