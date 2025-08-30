package dev.cacassiano.review_analizr.core.entities.analyze;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class Review {
    float stars;
    String discription;
    int likes;
    LocalDate issued_at;
}
