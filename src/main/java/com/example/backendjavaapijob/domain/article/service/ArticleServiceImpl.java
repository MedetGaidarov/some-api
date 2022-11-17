package com.example.backendjavaapijob.domain.article.service;


import com.example.backendjavaapijob.domain.article.model.Article;
import com.example.backendjavaapijob.domain.article.repository.ArticleRepository;
import com.example.backendjavaapijob.domain.user.model.User;
import com.example.backendjavaapijob.domain.user.service.UserService;
import com.example.backendjavaapijob.ui.dto.article.request.ArticleRequest;
import com.example.backendjavaapijob.ui.dto.article.response.ArticleDto;
import com.example.backendjavaapijob.ui.dto.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

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
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public ArticleDto createArticle(ArticleRequest articleRequest) {

        User author = userService.getUserById(1L);

        Article article = new Article();
        article.setAuthor(author);
        article.setContent(articleRequest.getContent());
        article.setTitle(articleRequest.getTitle());
        article.setPublish_date(articleRequest.getPublish_date());
        return articleMapper.toArticleDto(articleRepository.save(article));
    }
}
