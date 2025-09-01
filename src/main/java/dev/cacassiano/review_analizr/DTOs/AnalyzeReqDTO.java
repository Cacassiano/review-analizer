package dev.cacassiano.review_analizr.DTOs;

import org.hibernate.validator.constraints.URL;

import dev.cacassiano.review_analizr.core.entities.analyze.SupportedPlataforms;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class AnalyzeReqDTO {
    @NotBlank
    @URL
    String url;

    @NotBlank
    String plataform;
    public AnalyzeReqDTO (String url, String plataform) {
        // se a plataforma não for suportada lança excessão
        SupportedPlataforms.valueOf(plataform.toUpperCase());
        this.plataform = plataform;
        this.url = url;
    }
}