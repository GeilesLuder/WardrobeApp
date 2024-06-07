package com.example.wardrobeapp.service;

import com.example.wardrobeapp.model.Category;
import com.example.wardrobeapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

//@Service marks the class as Spring service component
@Service
public class CategoryService {
//@Autowired for connecting with reps
    @Autowired
    private CategoryRepository categoryRepository;
    
    //Getter for all categories
    public List<Category> getAll() {
        return (List<Category>) categoryRepository.findAll();
    }
    //Getter for category by searching its name 
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
    //Saves a category
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
    //Deletes a category with the id
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
