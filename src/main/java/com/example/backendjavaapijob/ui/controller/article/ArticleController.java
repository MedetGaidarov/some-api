package com.example.backendjavaapijob.ui.controller.article;


import com.example.backendjavaapijob.domain.article.service.ArticleService;
import com.example.backendjavaapijob.ui.dto.DefaultResponseDto;
import com.example.backendjavaapijob.ui.dto.article.request.ArticleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/article")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public ResponseEntity<Object> getAllArticles()
    {
        return ResponseEntity.ok(articleService.findAll());
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
