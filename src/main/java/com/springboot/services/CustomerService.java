package com.springboot.services;

import com.springboot.dao.CustomerDAO;
import com.springboot.models.Customer;
import com.springboot.models.CustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerDAO customerDAO;

    public void save(Customer customer) {
        customerDAO.save(customer);
    }

    public void deleteById(int id) {
        customerDAO.deleteById(id);
    }

    public void updateById(int id, CustomerDTO customerDTO) {
        Customer customerToUpdate = customerDAO.findById(id).get();
        if (customerToUpdate.getId() == id) {
            customerToUpdate.setName(customerDTO.getUsername());
            customerToUpdate.setSurname(customerDTO.getSurname());
            customerToUpdate.setEmail(customerDTO.getEmail());
            customerDAO.save(customerToUpdate);
        } else {
            throw new RuntimeException();
        }

    }
}

