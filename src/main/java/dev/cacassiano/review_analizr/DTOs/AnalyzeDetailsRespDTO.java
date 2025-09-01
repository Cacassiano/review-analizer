package dev.cacassiano.review_analizr.DTOs;

import java.util.List;
import java.util.Map;

import dev.cacassiano.review_analizr.core.entities.analyze.Analyze;
import dev.cacassiano.review_analizr.core.entities.analyze.Review;

public record AnalyzeDetailsRespDTO(
    String plataform,
    String url,
    List<Review> reviews,
    int num_reviews,
    Map<Float, Long> reviews_per_stars,
    int numPositives,
    int numNegatives,
    List<Review> mostLikedComments,
    List<Review> recentComments
){
    public AnalyzeDetailsRespDTO(Analyze analyze) {
        this(
            analyze.getPlataform(),
            analyze.getUrl(),
            analyze.getReviews(),
            analyze.getNum_reviews(),
            analyze.getReviews_per_stars(),
            analyze.getNumPositives(),
            analyze.getNumPositives(),
            analyze.getMostLikedComments(),
            analyze.getRecentComments()
        );
    }
}
