package dev.cacassiano.review_analizr.adapters.services;

import dev.cacassiano.review_analizr.core.entities.analyze.Analyze;

public interface ScapperService {
    Analyze scrappySite(PlataformService plataformService);
}