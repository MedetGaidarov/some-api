package com.example.backendjavaapijob.domain.content.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Content {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    String name = "dsfsdf";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
