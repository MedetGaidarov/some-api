package com.example.backendjavaapijob.domain.article.service;


import com.example.backendjavaapijob.domain.article.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    Optional<Article> getArticleById(Long id);
    List<Article> findAll();


}
