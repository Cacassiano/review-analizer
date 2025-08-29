package dev.cacassiano.review_analizr.core.entities.analyze;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    Map<String, Integer> stars;
    int numPositives;
    int numNegatives;
    
    List<Comment> mostLikedComments;
    List<Comment> recentComments;

    Set<String> strengths;
    Set<String> weakness;
}
