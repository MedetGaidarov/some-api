package com.example.backendjavaapijob.domain.article.repository;

import com.example.backendjavaapijob.domain.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT count(a) from Article a where a.publish_date between ?1 and ?2")
    Long countArticlesBetweenCurrentDateAndBeforeDate(LocalDateTime currentDate, LocalDateTime beforeDate);
}
