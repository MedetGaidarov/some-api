package com.example.backendjavaapijob.domain.article.model;


import com.example.backendjavaapijob.domain.user.model.User;
import com.example.backendjavaapijob.infrastructure.converter.LocalDateTimeAttributeConverter;
import com.example.backendjavaapijob.infrastructure.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 100)
    private String title;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    public User author;

    @Column(name = "content")
    private String content;

    @Column(name = "publish_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm'Z'")
    private LocalDateTime publish_date;



}
