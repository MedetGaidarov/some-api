package com.example.backendjavaapijob.domain.article.service;


import com.example.backendjavaapijob.domain.article.model.Article;
import com.example.backendjavaapijob.ui.dto.article.request.ArticleRequest;
import com.example.backendjavaapijob.ui.dto.article.response.ArticleDto;
import javassist.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface ArticleService {

    Optional<Article> getArticleById(Long id);
    List<Article> findAll();
    ArticleDto createArticle(ArticleRequest articleRequest) throws NotFoundException;

    Optional<Article> getArticleByDate(Date date);
}
