package com.example.backendjavaapijob.ui.dto.article.response;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ArticleDto {
    Long id;
    String title;
    String content;
    Date publish_date;

}
