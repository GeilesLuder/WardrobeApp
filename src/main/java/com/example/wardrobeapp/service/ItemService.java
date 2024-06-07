package com.example.wardrobeapp.service;

import com.example.wardrobeapp.model.Item;
import com.example.wardrobeapp.repository.CategoryRepository;
import com.example.wardrobeapp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    //@Autowired for connecting with reps
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    //Gets the items by searching the  user ID
    public List<Item> getItemsByUserId(Long userId) {
        return itemRepository.findByCustomerId(userId);
    }

    //Gets the items by searching the user ID and the category ID
    public List<Item> getItemsByUserIdAndCategoryId(Long userId, Long categoryId) {
        return itemRepository.findByCustomerIdAndCategoryId(userId, categoryId);
    }

    //Gets the item by searching the item ID
    public Optional<Item> getItemById(Long itemId) {
        return itemRepository.findById(itemId);
    }

    //Saves the items
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    //Deletes the items
    public Boolean deleteItem(Long itemId) {
        try{
             itemRepository.deleteById(itemId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //Updates a category of an item
    public Item updateItemCategory(Long itemId, Long categoryId) {
        Optional<Item> itemOpt = itemRepository.findById(itemId);
        if (itemOpt.isPresent()) {
            Item item = itemOpt.get();
            item.setCategory(categoryRepository.findById(categoryId).orElse(null));
            return itemRepository.save(item);
        }
        return null;
    }

    // Gett all items
    public List<Item> getAllItems() {
        return (List<Item>) itemRepository.findAll();
    }
}
