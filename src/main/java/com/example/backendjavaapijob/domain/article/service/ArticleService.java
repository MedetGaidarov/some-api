package com.example.backendjavaapijob.domain.article.service;


import com.example.backendjavaapijob.domain.article.model.Article;
import com.example.backendjavaapijob.ui.dto.article.request.ArticleRequest;
import com.example.backendjavaapijob.ui.dto.article.response.ArticleDto;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    Optional<Article> getArticleById(Long id);
    Page<Article> findAll(Pageable pageable);
    ArticleDto createArticle(ArticleRequest articleRequest) throws NotFoundException;



}
