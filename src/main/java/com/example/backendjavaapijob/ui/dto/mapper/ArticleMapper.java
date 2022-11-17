package com.example.backendjavaapijob.ui.dto.mapper;


import com.example.backendjavaapijob.domain.article.model.Article;
import com.example.backendjavaapijob.ui.dto.article.response.ArticleDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);
    Article toArticle(ArticleDto articleDto);
    ArticleDto toArticleDto(Article article);


}
