package com.example.backendjavaapijob.ui.controller.statistics;


import com.example.backendjavaapijob.domain.article.service.ArticleStatisticsService;
import com.example.backendjavaapijob.infrastructure.values.DefaultValues;
import com.example.backendjavaapijob.ui.dto.DefaultResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/statistics")
public class StatisticsController {


    @Autowired
    private ArticleStatisticsService articleStatisticsService;


    @GetMapping("article-count")
    public ResponseEntity<Object> articleCount(@RequestParam(required = false) Long days) {
        try {
            if(days == null ) days = DefaultValues.DEFAULT_DAY_FOR_STATISTICS;
            return ResponseEntity.ok(new DefaultResponseDto("Success", String.format("Articles count for %s days", days), articleStatisticsService.articleCountByDays(days)));

        } catch (Exception e) {
            return ResponseEntity.ok(new DefaultResponseDto("Fault", "no article count", "-7"));
        }

    }

}
