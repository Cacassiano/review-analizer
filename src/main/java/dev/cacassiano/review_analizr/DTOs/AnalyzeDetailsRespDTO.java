package dev.cacassiano.review_analizr.DTOs;

import java.util.List;
import java.util.Map;

import dev.cacassiano.review_analizr.core.entities.analyze.Analyze;

public record AnalyzeDetailsRespDTO(
    String plataform,
    String url,
    List<ReviewRespDTO> reviews,
    int num_reviews,
    Map<Float, Long> reviews_per_stars,
    int numPositives,
    int numNegatives,
    List<ReviewRespDTO> mostLikedComments,
    List<ReviewRespDTO> recentComments
){
    public AnalyzeDetailsRespDTO(
        Analyze analyze,
        List<ReviewRespDTO> reviews,
        Map<Float, Long> reviews_per_stars,
        List<ReviewRespDTO> mostLikedComments,
        List<ReviewRespDTO> recentComments  
    ) {
        this(
            analyze.getPlataform(),
            analyze.getUrl(),
            reviews,
            analyze.getNum_reviews(),
            reviews_per_stars,
            analyze.getNumPositives(),
            analyze.getNumPositives(),
            mostLikedComments,
            recentComments
        );
    }
}
