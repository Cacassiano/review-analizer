package dev.cacassiano.review_analizr.DTOs;

import java.time.LocalDate;

import dev.cacassiano.review_analizr.core.entities.analyze.Review;

public record ReviewRespDTO(
    float stars,
    String discription,
    int likes,
    LocalDate issued_at
) {
    public ReviewRespDTO(Review review) {
        this(
            review.getStars(),
            review.getDiscription(),
            review.getLikes(),
            review.getIssued_at()
        );

    }
}
