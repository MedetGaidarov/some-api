package com.example.backendjavaapijob.ui.dto.article.request;


import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest {

    String title;

    String content;

}
