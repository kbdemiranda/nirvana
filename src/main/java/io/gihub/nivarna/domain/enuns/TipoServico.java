package io.gihub.nivarna.domain.enuns;

import lombok.Getter;

@Getter
public enum TipoServico {
    STREAMING_VIDEO("Streaming de Vídeo"),
    STREAMING_MUSICA("Streaming de Música"),
    SOFTWARE("Software"),
    JOGOS("Jogos"),
    NOTICIAS("Notícias");

    private final String descricao;

    TipoServico(String descricao) {
        this.descricao = descricao;
    }
}

