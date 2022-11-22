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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        List<Article> articles = articleRepository.findAll();

        return articles;
    }

    @Override
    public ArticleDto createArticle(ArticleRequest articleRequest) throws NotFoundException {

        String currentUser = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> author = userService.findByUsername(currentUser);
        Article article = new Article();
        author.ifPresent(article::setAuthor);
        article.setContent(articleRequest.getContent());
        article.setTitle(articleRequest.getTitle());
        article.setPublish_date(new Date());

        return articleMapper.toArticleDto(articleRepository.save(article));
    }

    @Override
    public Optional<Article> getArticleByDate(Date date) {
        return articleRepository.getsArticleByPublish_date(date);
    }


}
