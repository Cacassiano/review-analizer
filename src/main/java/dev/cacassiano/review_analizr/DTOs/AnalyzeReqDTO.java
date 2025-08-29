package dev.cacassiano.review_analizr.DTOs;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class AnalyzeReqDTO {
    @NotBlank
    @URL
    String url;
}
