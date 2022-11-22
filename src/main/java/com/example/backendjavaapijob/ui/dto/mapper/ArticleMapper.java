package com.example.backendjavaapijob.ui.dto.mapper;


import com.example.backendjavaapijob.domain.article.model.Article;
import com.example.backendjavaapijob.ui.dto.article.response.ArticleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);
    @Mapping(target = "id", source = "articleDto.id")
    @Mapping(target = "title", source = "articleDto.title")
    @Mapping(target = "content", source = "articleDto.content")
    @Mapping(target = "publish_date", source = "articleDto.publish_date")

    Article toArticle(ArticleDto articleDto);
    @Mapping(target = "id", source = "article.id")
    @Mapping(target = "title", source = "article.title")
    @Mapping(target = "content", source = "article.content")
    @Mapping(target = "publish_date", source = "article.publish_date")

    ArticleDto toArticleDto(Article article);


}
