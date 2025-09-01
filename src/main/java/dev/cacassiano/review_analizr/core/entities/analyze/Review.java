package dev.cacassiano.review_analizr.core.entities.analyze;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Entity(name="reviews")
@Table(name="reviews")
public class Review {
    @Id @Column(unique=true, nullable=false, name="id")
    Long id;
    @Column(unique=false, nullable=false, name="stars")
    float stars;
    @Column(unique=false, nullable=false, name="discription")
    String discription;
    @Column(unique=false, nullable=false, name="likes")
    int likes;
    @Column(unique=false, nullable=false, name="issued_at")
    LocalDate issued_at;

    @ManyToOne
    @JoinColumn(name="analyze_id", nullable=false)
    private Analyze analyze; 
}
