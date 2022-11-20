package com.example.backendjavaapijob.ui.dto.article.response;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@Getter
@Setter
public class ArticleDto {
    Long id;
    String title;
    String content;
    LocalDateTime publish_date;

}
