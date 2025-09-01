package dev.cacassiano.review_analizr.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.cacassiano.review_analizr.core.entities.analyze.Analyze;

public interface AnalyzeRepository extends JpaRepository<Analyze, Long>{
    
}
