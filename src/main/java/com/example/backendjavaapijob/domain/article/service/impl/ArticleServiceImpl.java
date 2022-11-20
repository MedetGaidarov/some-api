package com.example.backendjavaapijob.domain.article.service.impl;


import com.example.backendjavaapijob.domain.article.model.Article;
import com.example.backendjavaapijob.domain.article.repository.ArticleRepository;
import com.example.backendjavaapijob.domain.article.service.ArticleService;
import com.example.backendjavaapijob.domain.user.model.User;
import com.example.backendjavaapijob.domain.user.service.UserService;
import com.example.backendjavaapijob.ui.dto.article.request.ArticleRequest;
import com.example.backendjavaapijob.ui.dto.article.response.ArticleDto;
import com.example.backendjavaapijob.ui.dto.mapper.ArticleMapper;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {


    private final ArticleMapper articleMapper;

    private final ArticleRepository articleRepository;

    private final UserService userService;

    public ArticleServiceImpl(ArticleMapper articleMapper,
                              ArticleRepository articleRepository, UserService userService) {
        this.articleMapper = articleMapper;
        this.articleRepository = articleRepository;
        this.userService = userService;
    }


    @Override
    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public Page<Article> findAll(Pageable pageable) {

        Page<Article> articles = articleRepository.findAll(pageable);
        return articles;
    }

    @Override
    public ArticleDto createArticle(ArticleRequest articleRequest) throws NotFoundException {

        Optional<User> author = userService.findByUsername("mgaidarov");

        Article article = new Article();
        author.ifPresent(article::setAuthor);
        article.setContent(articleRequest.getContent());
        article.setTitle(articleRequest.getTitle());
        article.setPublish_date(LocalDateTime.now());

        return articleMapper.toArticleDto(articleRepository.save(article));
    }
}
