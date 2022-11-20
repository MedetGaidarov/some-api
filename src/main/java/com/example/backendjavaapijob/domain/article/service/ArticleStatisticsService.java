package com.example.backendjavaapijob.domain.article.service;


import com.example.backendjavaapijob.domain.article.model.Article;

import java.util.Optional;

public interface ArticleStatisticsService {
    public Long articleCountByDays(Long days);
    public Optional<Article> articleGet();
}
