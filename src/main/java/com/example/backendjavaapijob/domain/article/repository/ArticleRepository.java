package com.example.backendjavaapijob.domain.article.repository;

import com.example.backendjavaapijob.domain.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a from Article a where a.id = 20 ")
    Optional<Article> customFindById();



    @Query("SELECT a from Article a where a.publish_date = ?1 ")
    Optional<Article> getsArticleByPublish_date(Date date);



    @Query("SELECT count(a) from Article a where a.publish_date between ?1 and ?2")
    Long countArticlesBetweenCurrentDateAndBeforeDate(Date currentDate, Date beforeDate);
}
