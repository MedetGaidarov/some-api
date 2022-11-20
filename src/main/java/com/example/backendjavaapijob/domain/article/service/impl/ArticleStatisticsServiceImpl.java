package com.example.backendjavaapijob.domain.article.service.impl;


import com.example.backendjavaapijob.domain.article.model.Article;
import com.example.backendjavaapijob.domain.article.repository.ArticleRepository;
import com.example.backendjavaapijob.domain.article.service.ArticleStatisticsService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.OptionalInt;


@Service
public class ArticleStatisticsServiceImpl implements ArticleStatisticsService {

    @Autowired
    private ArticleRepository articleRepository;




    @Override
    public Long articleCountByDays(Long days) {
        Date currentDate = new Date();
        Date beforeDate = new DateTime(currentDate).minusDays(300).toDate();

        Long article = articleRepository.countArticlesBetweenCurrentDateAndBeforeDate(currentDate, beforeDate);
        return article;
    }

    @Override
    public Optional<Article> articleGet() {
        Optional<Article> article = articleRepository.customFindById();
        return  article;
    }
}
