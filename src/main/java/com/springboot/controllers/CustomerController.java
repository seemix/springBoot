package com.springboot.controllers;

import com.springboot.dao.CustomerDAO;
import com.springboot.models.Customer;
import com.springboot.models.CustomerDTO;
import com.springboot.models.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.springboot.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    private CustomerDAO customerDAO;
    private CustomerService customerService;

    @GetMapping("")
    @JsonView(Views.Client.class)
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> all = customerDAO.findAll();
        return new ResponseEntity<>(all, HttpStatusCode.valueOf(200));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Customer customer) {
        customerService.save(customer);
    }

    @GetMapping("/{id}")
    @JsonView(Views.Admin.class)
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        Customer customer = customerDAO.findById(id).get();
        return new ResponseEntity<>(customer, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDTO) {
        customerService.updateById(id, customerDTO);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Customer>> getCustomersByName(@PathVariable String name) {
        return new ResponseEntity<>(customerDAO.getByName(name), HttpStatusCode.valueOf(200));
    }
}
