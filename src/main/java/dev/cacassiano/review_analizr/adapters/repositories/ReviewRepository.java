package dev.cacassiano.review_analizr.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.cacassiano.review_analizr.core.entities.analyze.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
    
}
