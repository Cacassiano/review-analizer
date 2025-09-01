package dev.cacassiano.review_analizr.adapters.services;

import dev.cacassiano.review_analizr.DTOs.AnalyzeDetailsRespDTO;
import dev.cacassiano.review_analizr.core.entities.analyze.Analyze;

public interface AnalyzeService {
    public Analyze createAnalyze(String url, String plataform);
    public AnalyzeDetailsRespDTO getAnalyzeDetails(Long id, Integer maxReviews, Integer maxLikedReviews, Integer maxRecentComments);
}
