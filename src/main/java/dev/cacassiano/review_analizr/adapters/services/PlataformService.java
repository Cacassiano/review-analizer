package dev.cacassiano.review_analizr.adapters.services;

import java.util.List;
import java.util.Map;

import dev.cacassiano.review_analizr.core.entities.analyze.Comment;

public interface PlataformService {
    Map<Integer, List<Comment>> getComments(String url);
}
