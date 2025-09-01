package dev.cacassiano.review_analizr.core.entities.analyze;

import java.util.List;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "analyzes")
@Table(name="analyzes")
public class Analyze {
    @Id @Column(unique=true, nullable=false, name="id")
    Long id;
    @Column(unique=false,nullable=false, name="plataform")
    String plataform;
    @Column(unique=true, nullable=false, name="url")
    String url;
    
    

    @Column(unique=false, nullable=false, name="num_reviews")
    int num_reviews;
    @Column(unique=false, nullable=false, name="num_positives")
    int numPositives;
    @Column(unique=false, nullable=false, name="num_negatives")
    int numNegatives;
    
    @OneToMany(mappedBy="id")
    List<Review> reviews;

    // List<Review> mostLikedComments;
    // List<Review> recentComments;
    // Map<Float, Long> reviews_per_stars;
}
