package dev.cacassiano.review_analizr.adapters.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.cacassiano.review_analizr.DTOs.AnalyzeCompleteRespDTO;
import dev.cacassiano.review_analizr.DTOs.AnalyzeDetailsRespDTO;
import dev.cacassiano.review_analizr.DTOs.AnalyzeReqDTO;
import dev.cacassiano.review_analizr.adapters.services.AnalyzeService;
import dev.cacassiano.review_analizr.core.entities.analyze.Analyze;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/analyzes")
public class AnalizerController {
    
    @Autowired
    private AnalyzeService analyzeService;    
    
    @PostMapping
    public ResponseEntity<AnalyzeCompleteRespDTO> createAnalyze(@RequestBody @Valid AnalyzeReqDTO req) throws IOException{
        Analyze analyze = analyzeService.createAnalyze(req.getUrl(), req.getPlataform().toLowerCase());
        return ResponseEntity.status(201).body(new AnalyzeCompleteRespDTO("completed", analyze.getId()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<AnalyzeDetailsRespDTO> getDetails(
        @PathVariable Long id,
        @RequestParam("maxReviews") Integer maxReviews,
        @RequestParam("maxLikedReviews") Integer maxLikedReviews,
        @RequestParam("maxRecentReviews") Integer maxRecentReviews
    ) {
        AnalyzeDetailsRespDTO analyze = analyzeService.getAnalyzeDetails(
            id, 
            maxReviews,
            maxLikedReviews,
            maxRecentReviews
        );
        return ResponseEntity.ok(analyze);
    }
}
