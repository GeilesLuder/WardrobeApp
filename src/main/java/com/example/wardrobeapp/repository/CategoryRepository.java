package com.example.wardrobeapp.repository;

import com.example.wardrobeapp.model.Category;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByName(String name);

    // Is already defined in CrudRepository
    //Category save (Category category);
    //Long deleteById (Long id);
}