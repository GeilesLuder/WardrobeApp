package com.example.wardrobeapp.service;

import com.example.wardrobeapp.model.Category;
import com.example.wardrobeapp.model.Customer;
import com.example.wardrobeapp.model.Item;
import com.example.wardrobeapp.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class InitService {


    @Autowired
    private CustomerService customerService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private PictureService pictureService;

    public void initDB() {
        // ---------------- Customer ---------------- //

        Customer customer1 = new Customer();
        customer1.setEmail("customer1@gmail.com");
        customer1.setName("Customer 1");
        customer1.setPassword("password");
        customer1 = customerService.saveUser(customer1);

        Customer customer2 = new Customer();
        customer2.setEmail("customer2@gmail.com");
        customer2.setName("Customer 2");
        customer2.setPassword("password");
        customer2 = customerService.saveUser(customer2);

        Customer customer3 = new Customer();
        customer3.setEmail("customer3@gmail.com");
        customer3.setName("Customer 3");
        customer3.setPassword("password");
        customer3 = customerService.saveUser(customer3);

        // ---------------- Category ---------------- //

        Category category1 = new Category();
        category1.setName("Basics");
        category1.setDescription("Basics are just plain shirts");
        category1 = categoryService.saveCategory(category1);

        Category category2 = new Category();
        category2.setName("Footwear");
        category2.setDescription("Footwear items including shoes, sandals, etc.");
        category2 = categoryService.saveCategory(category2);

        Category category3 = new Category();
        category3.setName("Accessories");
        category3.setDescription("Accessories including bags, belts, hats, etc.");
        category3 = categoryService.saveCategory(category3);

        Category category4 = new Category();
        category4.setName("Shorts");
        category4.setDescription("Shorts are Pants");
        category4 = categoryService.saveCategory(category4);

        Category category5 = new Category();
        category5.setName("Dress");
        category5.setDescription("Dresses are garments to wear");
        category5 = categoryService.saveCategory(category5);

        // ---------------- Picture ---------------- //
        Picture picture1 = new Picture();
        picture1.setFile(new File("C:\\Users\\neuba\\IdeaProjects\\WardrobeApp\\src\\main\\resources\\pictures\\shirt.png"));
        pictureService.save(picture1);

        Picture picture2 = new Picture();
        picture2.setFile(new File("C:\\Users\\neuba\\IdeaProjects\\WardrobeApp\\src\\main\\resources\\pictures\\dress.png"));
        pictureService.save(picture2);

        Picture picture3 = new Picture();
        picture3.setFile(new File("C:\\Users\\neuba\\IdeaProjects\\WardrobeApp\\src\\main\\resources\\pictures\\shoes.png"));
        pictureService.save(picture3);

        Picture picture4 = new Picture();
        picture4.setFile(new File("C:\\Users\\neuba\\IdeaProjects\\WardrobeApp\\src\\main\\resources\\pictures\\bag.png"));
        pictureService.save(picture4);


        // ---------------- Item ---------------- //

        Item item1 = new Item();
        item1.setCategory(category1);
        item1.setPicture(picture1);
        item1.setName("Black shirt with Print");
        item1.setCustomer(customer1);
        itemService.saveItem(item1);

        Item item2 = new Item();
        item2.setCategory(category1);
        item2.setPicture(picture2);
        item2.setName("Cute black dress");
        item2.setCustomer(customer1);
        itemService.saveItem(item2);

        Item item3 = new Item();
        item3.setCategory(category2);
        item3.setPicture(picture3);
        item3.setName("Sneaker");
        item3.setCustomer(customer1);
        itemService.saveItem(item3);

        Item item4 = new Item();
        item4.setCategory(category3);
        item4.setPicture(picture4);
        item4.setName("Leather Bag");
        item4.setCustomer(customer1);
        itemService.saveItem(item4);

    }
}
