package com.example.wardrobeapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// youll need a ID with @ID and @GeneratedValue
// name, maybe description? 
// getters setters obv
// this is only a "lookup table" so we can link the items to a category, we should not need to change any of the data in this
@Entity
@Table(name = "category")
public class Category {

  //private string variable name
  @Setter
  @Getter
  private String name;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  
  //Description
  @Setter
  @Getter
  private String description;

  //Constructor Category
  public Category (){

  }

}
