package dev.cacassiano.review_analizr.core.entities.analyze;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @Id @Column(unique=true, nullable=false, name="id") @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    @Column(unique=false,nullable=false, name="plataform")
    String plataform;
    // TODO fazer ser unico para uma analise para cada url
    @Column(unique=false, nullable=false, name="url", length=1024)
    String url;
    
    

    @Column(unique=false, nullable=false, name="num_reviews")
    int num_reviews;
    @Column(unique=false, nullable=false, name="num_positives")
    int numPositives;
    @Column(unique=false, nullable=false, name="num_negatives")
    int numNegatives;
    
    @OneToMany(mappedBy="analyze", cascade=CascadeType.REMOVE)
    List<Review> reviews;
}
