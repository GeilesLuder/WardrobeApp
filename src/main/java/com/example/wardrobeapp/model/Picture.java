package com.example.wardrobeapp.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    // if you find a better solution go for it
    @Getter
    @Setter
    private File file;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
