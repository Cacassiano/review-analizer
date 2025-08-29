package dev.cacassiano.review_analizr.use_cases;

import org.springframework.stereotype.Service;

import dev.cacassiano.review_analizr.DTOs.AnalyzeReqDTO;
import dev.cacassiano.review_analizr.adapters.services.AnalyzeService;

@Service
public class AnalyzeServiceImpl implements AnalyzeService{

    @Override
    public AnalyzeReqDTO createAnalyze(String url, String plataforma) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
