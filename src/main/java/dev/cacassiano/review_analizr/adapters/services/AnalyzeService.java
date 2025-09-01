package dev.cacassiano.review_analizr.adapters.services;

import dev.cacassiano.review_analizr.core.entities.analyze.Analyze;

public interface AnalyzeService {
    Analyze createAnalyze(String url, String plataform);

    public Analyze getAnalyze(Long id);
}
