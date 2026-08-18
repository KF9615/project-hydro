package com.example.inventory.service;

import com.example.inventory.entity.Customer;
import com.example.inventory.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        if (customerRepository.existsByCustomerCodeIgnoreCase(
                customer.getCustomerCode())) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Customer already exists");
        }

        return customerRepository.save(customer);
        }

        public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        }

        public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existingCustomer = getCustomerById(id);

        boolean customerCodeHasChanged = !existingCustomer.getCustomerCode().equalsIgnoreCase(updatedCustomer.getCustomerCode());

        if (customerCodeHasChanged && customerRepository.existsByCustomerCodeIgnoreCase(
                updatedCustomer.getCustomerCode())) {

            throw new  ResponseStatusException(
                    HttpStatus.CONFLICT, "Customer code already exists");
        }

        existingCustomer.setCustomerCode(updatedCustomer.getCustomerCode());
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setContactNumber(updatedCustomer.getContactNumber());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        existingCustomer.setActive(updatedCustomer.isActive());

        return customerRepository.save(existingCustomer);
        }

        public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
        }
    }
