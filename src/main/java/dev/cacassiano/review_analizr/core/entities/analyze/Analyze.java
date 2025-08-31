package dev.cacassiano.review_analizr.core.entities.analyze;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Analyze {
    // TODO Plataform enum;
    String plataform;
    String url;
    List<Review> reviews;

    int num_reviews;
    Map<Float, Long> reviews_per_stars;
    int numPositives;
    int numNegatives;
    
    List<Review> mostLikedComments;
    List<Review> recentComments;
}
