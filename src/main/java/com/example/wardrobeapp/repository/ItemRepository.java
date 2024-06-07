package com.example.wardrobeapp.repository;

import com.example.wardrobeapp.model.Item;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByCustomerId(Long customerId);
    List<Item> findByCustomerIdAndCategoryId(Long customerId, Long categoryId);
    
    // Is already defined in CrudRepository
    //List<Optional> findById (Long itemId);
    //Item save(Item item);
    // void deleteById(Long itemId);
}