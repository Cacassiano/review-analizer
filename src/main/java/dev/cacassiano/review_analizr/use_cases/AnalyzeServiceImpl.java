package dev.cacassiano.review_analizr.use_cases;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.cacassiano.review_analizr.DTOs.AnalyzeDetailsRespDTO;
import dev.cacassiano.review_analizr.DTOs.ReviewRespDTO;
import dev.cacassiano.review_analizr.adapters.repositories.AnalyzeRepository;
import dev.cacassiano.review_analizr.adapters.repositories.ReviewRepository;
import dev.cacassiano.review_analizr.adapters.services.AnalyzeService;
import dev.cacassiano.review_analizr.adapters.services.PlataformService;
import dev.cacassiano.review_analizr.core.entities.analyze.Analyze;
import dev.cacassiano.review_analizr.core.entities.analyze.Review;

@Service
public class AnalyzeServiceImpl implements AnalyzeService{

    @Autowired
    private Map<String, PlataformService> plataformServices;
    @Autowired
    private AnalyzeRepository analyzeRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    
    private final int defaultMaxReviews = 3;

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
        Analyze analyzeEntity = analyzeRepository.save(analyze);
        reviews.forEach(review -> {
            review.setAnalyze(analyzeEntity);
            reviewRepository.save(review);
        });
        return analyze;
    }

    private Map<Float, Long> getReviewsPerStar(List<ReviewRespDTO> reviews) {
        return reviews.stream()
            .collect(Collectors.groupingBy(
                    ReviewRespDTO::stars,
                    Collectors.counting()
            ));
    }

    private List<ReviewRespDTO> getRecently(List<ReviewRespDTO> reviews, int max_reviews) {
        List<ReviewRespDTO> reviewsSortedByDate = reviews.stream()
            .sorted(Comparator.comparing(ReviewRespDTO::issued_at).reversed())
            .toList();
        if(!reviewsSortedByDate.isEmpty()){
            return reviewsSortedByDate.subList(0, max_reviews);
        }
        return null;
    }
    
    private List<ReviewRespDTO> getMostLiked(List<ReviewRespDTO> reviews, int max_reviews) {
        List<ReviewRespDTO> reviewSortedByLikes = reviews.stream()
            .filter(r -> r.likes()>0)
            .sorted(Comparator.comparingInt(ReviewRespDTO::likes).reversed())
            .toList();
        if(!reviewSortedByLikes.isEmpty()) {
            int maxSize = (reviewSortedByLikes.size()>max_reviews ? max_reviews : reviewSortedByLikes.size());
            return reviewSortedByLikes.subList(0,maxSize);
        }
        return null;
    }

    @Override
    public AnalyzeDetailsRespDTO getAnalyzeDetails(Long id, Integer maxReviews, Integer maxLikedReviews, Integer maxRecentReviews) {
        Analyze analyze = analyzeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException());
        List<ReviewRespDTO> reviews = analyze.getReviews().stream().map(ReviewRespDTO::new).toList();
        System.out.println(reviews.size());
        
        int tempMax = Math.min((maxLikedReviews == null ? defaultMaxReviews: maxLikedReviews), reviews.size());
        List<ReviewRespDTO> mostliked = getMostLiked(reviews, tempMax);

        tempMax = Math.min((maxRecentReviews == null ? defaultMaxReviews: maxRecentReviews), reviews.size());
        List<ReviewRespDTO> recents = getRecently(reviews,  tempMax);

        Map<Float, Long> reviewsPerStars = getReviewsPerStar(reviews);

        tempMax = Math.min((maxReviews == null ? defaultMaxReviews: maxReviews), reviews.size());
        return new AnalyzeDetailsRespDTO(
            analyze, 
            reviews.subList(0, tempMax),
            reviewsPerStars, 
            mostliked, 
            recents
        );
    }
}
