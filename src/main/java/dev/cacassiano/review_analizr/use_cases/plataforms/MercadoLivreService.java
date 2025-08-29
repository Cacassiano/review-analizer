package dev.cacassiano.review_analizr.use_cases.plataforms;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import dev.cacassiano.review_analizr.adapters.services.PlataformService;
import dev.cacassiano.review_analizr.core.entities.analyze.Comment;

@Service("mercadolivre")
public class MercadoLivreService implements PlataformService {
    @Override
    public Map<Integer, List<Comment>> getComments(String url) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}