package com.example.wardrobeapp.repository;

import com.example.wardrobeapp.model.Customer;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

    // Is already defined in CrudRepository
    //User save(User user);
    //void deleteById(Long userId);
}