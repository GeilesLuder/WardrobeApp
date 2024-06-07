package com.example.wardrobeapp.repository;

import com.example.wardrobeapp.model.Picture;
import org.springframework.data.repository.CrudRepository;


public interface PictureRepository extends CrudRepository<Picture, Long> {
   
}