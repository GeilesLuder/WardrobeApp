package com.example.wardrobeapp.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Customer")
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    @Column(unique=true)
    private String email;

    @Getter
    @Setter
    private String password;


}