package dev.cacassiano.review_analizr.adapters.services;

import dev.cacassiano.review_analizr.DTOs.AnalyzeReqDTO;

public interface AnalyzeService {
    AnalyzeReqDTO createAnalyze(String url, String plataforma);
}
