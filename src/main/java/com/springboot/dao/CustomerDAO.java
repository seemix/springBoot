package com.springboot.dao;

import com.springboot.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {
    Customer findCustomerByLogin(String username);
}
