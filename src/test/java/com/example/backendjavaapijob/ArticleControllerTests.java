package com.example.backendjavaapijob;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest
public class ArticleControllerTests {



    @Autowired
    private TestRestTemplate testRestTemplate;
}
