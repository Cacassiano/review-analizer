package dev.cacassiano.review_analizr.use_cases.plataforms;

import java.util.List;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import dev.cacassiano.review_analizr.adapters.services.PlataformService;
import dev.cacassiano.review_analizr.core.entities.analyze.Review;

@Service("mercadolivre")
public class MercadoLivreService implements PlataformService {

    @Override
    public List<Review> getReviews(String url) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Review createReview(Element parent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}