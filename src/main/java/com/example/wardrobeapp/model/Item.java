package com.example.wardrobeapp.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// needs to have a name field
// relation to category 1:many -> categoryid as forgein key here
// realtion to user 1:many -> userid as forgein key here
// also have a look at cascading types if we want to delete stuff. for example:
// we do not want to delete a user if we delete a item that is related to that user
// same for categories.
@Entity
@Table(name = "Item")
public class Item {
  
  //Private variable String name
  @Getter
  @Setter
  private String name;


  @Id
  @Getter
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  //@ManyToOne indicates that many instances of the current entity can be related to the "Category"
  @ManyToOne
  //JoinColumn is used to specify the name of the column in the database that will be used to join the two tables (false is used to make sure that key column is not null)
  @JoinColumn(name = "category_id", nullable = false)
  @Getter
  @Setter
  private Category category;

  @ManyToOne
  @Getter
  @Setter
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @Getter
  @Setter
  @JoinColumn(name = "picture_id", nullable = false)
  @OneToOne
  private Picture picture;

  //Public constructor
  public Item (){
     
  }


}