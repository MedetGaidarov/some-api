package com.example.backendjavaapijob.domain.article.service.impl;


import com.example.backendjavaapijob.domain.article.repository.ArticleRepository;
import com.example.backendjavaapijob.domain.article.service.ArticleStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ArticleStatisticsServiceImpl implements ArticleStatisticsService {

    @Autowired
    private ArticleRepository articleRepository;


    @Override
    public Long articleCountByDays(Long days) {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime beforeDate = currentDate.minusDays(days);

        return articleRepository.countArticlesBetweenCurrentDateAndBeforeDate(currentDate, beforeDate);
    }
}
