package dev.cacassiano.review_analizr.use_cases;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.cacassiano.review_analizr.adapters.services.AnalyzeService;
import dev.cacassiano.review_analizr.adapters.services.PlataformService;
import dev.cacassiano.review_analizr.core.entities.analyze.Analyze;
import dev.cacassiano.review_analizr.core.entities.analyze.Review;

@Service
public class AnalyzeServiceImpl implements AnalyzeService{

    @Autowired
    private Map<String, PlataformService> plataformServices;
    private final int max_reviews = 3;

    @Override
    public Analyze createAnalyze(String url, String plataform) {
        Analyze analyze = new Analyze();
        analyze.setUrl(url);
        analyze.setPlataform(plataform);
        PlataformService currentService = plataformServices.get(plataform);
        List<Review> reviews = currentService.getReviews(url);

        analyze.setReviews(reviews);
        analyze.setNum_reviews(reviews.size());
        
        reviews.forEach(e -> {
            if(e.getStars() > 3) analyze.setNumPositives(analyze.getNumPositives() + 1);
            else analyze.setNumNegatives(analyze.getNumNegatives() + 1);
        });
        
        analyze.setReviews_per_stars(reviews.stream()
            .collect(Collectors.groupingBy(
                Review::getStars,
                Collectors.counting()))
        );
        
        analyze.setMostLikedComments(getMostLiked(reviews));
        analyze.setRecentComments(getRecently(reviews));

        return analyze;
    }

    public List<Review> getRecently(List<Review> reviews) {
        List<Review> reviewsSortedByDate = reviews.stream()
            .sorted(Comparator.comparing(Review::getIssued_at).reversed())
            .toList();
        if(!reviewsSortedByDate.isEmpty()){
            return reviewsSortedByDate.subList(0, max_reviews);
        }
        return null;
    }
    
    public List<Review> getMostLiked(List<Review> reviews) {
        List<Review> reviewSortedByLikes = reviews.stream()
            .filter(r -> r.getLikes()>0)
            .sorted(Comparator.comparingInt(Review::getLikes).reversed())
            .toList();
        if(!reviewSortedByLikes.isEmpty()) {
            int maxSize = (reviewSortedByLikes.size()>max_reviews ? max_reviews : reviewSortedByLikes.size());
            return reviewSortedByLikes.subList(0,maxSize);
        }
        return null;
    }
}
