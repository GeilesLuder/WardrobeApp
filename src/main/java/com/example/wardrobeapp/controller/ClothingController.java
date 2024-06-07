package com.example.wardrobeapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import com.example.wardrobeapp.model.Customer;
import com.example.wardrobeapp.model.Item;
import com.example.wardrobeapp.model.Picture;
import com.example.wardrobeapp.model.ResponseMessage;
import com.example.wardrobeapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;


import org.springframework.http.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class ClothingController {
  
  @Autowired
  private ItemService itemService;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private PictureService pictureService;

  @Autowired
  private ImageProcessingService imageProcessingService;


  // Get List of items from a specific user
  @GetMapping("/items/{customerMail}")
    public ResponseEntity<List<Item>> getItems(@PathVariable String customerMail) {
      Optional<Customer> currUser = customerService.getUserByEmail(customerMail);
      return new ResponseEntity<List<Item>>(itemService.getItemsByUserId(currUser.get().getId()), HttpStatus.OK);
    }

  // delete single item
  @GetMapping("/delete/{itemID}")
  public ResponseEntity<String> deleteItem(@PathVariable Long itemID) {
    
    if(itemService.deleteItem(itemID)){
      return new ResponseEntity<String>("Item deleted", HttpStatus.OK);
    }else{
      return new ResponseEntity<String>(HttpStatus.METHOD_FAILURE);
    }
  }



  @PostMapping("/upload/{customerMail}")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String customerMail) {
    String message = "";
    try {
      // get uer from email
      Optional<Customer> currUser = customerService.getUserByEmail(customerMail);
      // create new item and set variables
      Item newItem = new Item();
      newItem.setName(file.getOriginalFilename());

      newItem.setCustomer(currUser.get());
      Picture pic = new Picture();
      // process image
      pic.setFile(imageProcessingService.processImage(file));
      pictureService.save(pic);
      // set pic and save
      newItem.setPicture(pic);
      itemService.saveItem(newItem);

      message = "Uploaded the file successfully: " + file.getOriginalFilename() + "itemId " + newItem.getId();
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/download/{itemId}")
  public ResponseEntity<Resource> download( @PathVariable Long itemId) throws FileNotFoundException {

    Optional<Item> currItem = itemService.getItemById(itemId);
    File file = new File(currItem.get().getPicture().getFile().toURI());

    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

    return ResponseEntity.ok()
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
  }




  
  @GetMapping("/category/{categoryId}/{customerMail}")
  public List<Item> getCategories(@PathVariable Long categoryId, @PathVariable String customerMail) {

    return itemService.getItemsByUserIdAndCategoryId(customerService.getUserByEmail(customerMail).get().getId(), categoryId);
  }

  // Update the category of an item
  @PutMapping("items/update/{itemID}/{categoryID}")
  public ResponseEntity<String> updateItem(@PathVariable Long itemID, @PathVariable Long categoryID){
    itemService.updateItemCategory(itemID, categoryID);
    return new ResponseEntity<String>("Item updated", HttpStatus.OK);
  }

  
}