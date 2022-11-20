package com.example.backendjavaapijob.ui.controller.article;


import com.example.backendjavaapijob.domain.article.model.Article;
import com.example.backendjavaapijob.domain.article.service.ArticleService;
import com.example.backendjavaapijob.ui.dto.DefaultResponseDto;
import com.example.backendjavaapijob.ui.dto.article.request.ArticleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/article")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public ResponseEntity<Object> getAllArticles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    )
    {
        try
        {
            List<Article> articles;
            Pageable paging = PageRequest.of(page, size);
            Page<Article> pageArticles = articleService.findAll(paging);
            articles = pageArticles.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("articles", articles);
            response.put("currentPage", pageArticles.getNumber());
            response.put("totalItems", pageArticles.getTotalElements());
            response.put("totalPages", pageArticles.getTotalPages());
            return ResponseEntity.ok(new DefaultResponseDto("SUCCESS", "Article successfully found", response));
        } catch (Exception e) {
            return ResponseEntity.ok(new DefaultResponseDto("FAULT", e.getMessage()));

        }

    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getArticle(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(new DefaultResponseDto("SUCCESS", "Article successfully found", articleService.getArticleById(id)));
        } catch (Exception e) {
            return ResponseEntity.ok(new DefaultResponseDto("FAULT", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> createArticle(@RequestBody ArticleRequest articleRequest)
    {
        try
        {
            return ResponseEntity.ok(new DefaultResponseDto("Success", "Article succesfully created", articleService.createArticle(articleRequest)));

        }catch (Exception e)
        {
            return ResponseEntity.ok(new DefaultResponseDto("FAULT", e.getMessage()));
        }
    }



}
