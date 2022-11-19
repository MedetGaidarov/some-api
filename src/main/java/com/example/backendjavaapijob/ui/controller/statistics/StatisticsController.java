package com.example.backendjavaapijob.ui.controller.statistics;


import com.example.backendjavaapijob.ui.dto.DefaultResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/statistics")
public class StatisticsController {


    @GetMapping("article-count")
    public ResponseEntity<Object> articleCount(@RequestParam(required = false) String filter) {
        try {
            return ResponseEntity.ok(new DefaultResponseDto("SUCCESS", "Article count", "7"));

        } catch (Exception e) {
            return ResponseEntity.ok(new DefaultResponseDto("Fault", "no article count", "-7"));
        }

    }

}
