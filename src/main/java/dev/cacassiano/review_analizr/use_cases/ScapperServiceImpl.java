package dev.cacassiano.review_analizr.use_cases;

import org.springframework.stereotype.Service;

import dev.cacassiano.review_analizr.adapters.services.PlataformService;
import dev.cacassiano.review_analizr.adapters.services.ScapperService;
import dev.cacassiano.review_analizr.core.entities.analyze.Analyze;

@Service
public class ScapperServiceImpl implements ScapperService{

    @Override
    public Analyze scrappySite(PlataformService plataformService) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
