package com.example.backendjavaapijob.domain.article.model;


import com.example.backendjavaapijob.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(name = "title")
    private String title;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "author_id")
    public User author;

    @Column(name = "content")
    private String content;

    @Column(name = "publish_date")
    private Date publish_date;


}
