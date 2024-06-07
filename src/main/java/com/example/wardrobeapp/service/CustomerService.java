package com.example.wardrobeapp.service;

import com.example.wardrobeapp.model.Customer;
import com.example.wardrobeapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    //Gets the email of the user
    public Optional<Customer> getUserByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
    //Saves a user
    public Customer saveUser(Customer customer) {
        return customerRepository.save(customer);
    }
    //Deletes the user by the ID
    public void deleteUser(Long userId) {
        customerRepository.deleteById(userId);
    }
    //Verifies the inputs for login, and returns the user if it's valid
    // Maybe boolean? would be easier for controller
    public Boolean loginUser(String email, String password) {
        Optional<Customer> userOpt = customerRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            Customer customer = userOpt.get();
            if (customer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
