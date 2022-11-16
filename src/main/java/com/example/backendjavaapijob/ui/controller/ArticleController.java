package com.example.backendjavaapijob.ui.controller;


import com.example.backendjavaapijob.domain.article.model.Article;
import com.example.backendjavaapijob.domain.article.service.ArticleService;
import com.example.backendjavaapijob.ui.dto.DefaultResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("{id}")
    public ResponseEntity<Object> getUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(new DefaultResponseDto("SUCCESS", "User successfully found", articleService.getArticleById(id)));
        } catch (Exception e) {
            return ResponseEntity.ok(new DefaultResponseDto("FAULT", e.getMessage()));
        }
    }

}
