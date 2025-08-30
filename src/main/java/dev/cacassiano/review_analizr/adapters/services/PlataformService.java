package dev.cacassiano.review_analizr.adapters.services;

import java.util.List;

import org.jsoup.nodes.Element;

import dev.cacassiano.review_analizr.core.entities.analyze.Review;

public interface PlataformService {
    public List<Review> getReviews(String url);
    Review createReview(Element parent);
}
