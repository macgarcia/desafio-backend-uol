package com.github.macgarcia.backend.uol.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author macgarcia
 */
public class ResponseDto implements Serializable {
    
    private final int codigo;
    private final String mensagem;
    private final String dataHora;

    public ResponseDto(int codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.dataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(LocalDateTime.now());
    }

    public int getCodigo() {
        return codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getDataHora() {
        return dataHora;
    }
    
}
