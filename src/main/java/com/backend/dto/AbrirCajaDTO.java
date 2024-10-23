package com.backend.dto;

import java.time.LocalDateTime;

import com.backend.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AbrirCajaDTO {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaApertura;
    private Double saldoInicial;
    private Usuario usuario;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AbrirCajaDTO{");
        sb.append("fechaApertura=").append(fechaApertura);
        sb.append(", saldoInicial=").append(saldoInicial);
        sb.append(", usuario=").append(usuario);
        sb.append('}');
        return sb.toString();
    }
    
}
