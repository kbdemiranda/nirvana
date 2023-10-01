package io.gihub.nivarna.application.dto;

import io.gihub.nivarna.domain.enuns.TipoServico;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PlataformaDTO {

    private Long id;

    @NotNull(message = "O nome não pode ser nulo.")
    private String nome;

    @NotNull(message = "O preço não pode ser nulo.")
    @Positive(message = "O preço deve ser positivo.")
    private BigDecimal preco;

    @NotNull(message = "O tipo de serviço não pode ser nulo.")
    private TipoServico tipo;

    @NotNull(message = "O total de vagas não pode ser nulo.")
    @Positive(message = "O total de vagas deve ser positivo.")
    private Integer totalVagas;

    private Integer vagasDisponiveis;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private LocalDateTime dataExclusao;

}

