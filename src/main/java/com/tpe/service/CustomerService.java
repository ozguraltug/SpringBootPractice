package com.tpe.service;

import com.tpe.domain.Customer;
import com.tpe.dto.CustomerDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public void saveCustomer(Customer customer) {
        boolean isExistCustomer = customerRepository.
                existsByEmail(customer.getEmail());
        if (isExistCustomer) {
            throw new
                    ConflictException("Customer already exists by  " + customer.getEmail());
        }
        customerRepository.save(customer);

    }

    public List<Customer> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    public Customer getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Customer not found by id " + id));
        return customer;
    }

    public void deleteCustomerById(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }

    public CustomerDTO getCustomerDTOById(Long id) {
        Customer customer = getCustomerById(id);
//        CustomerDTO customerDTO=new CustomerDTO();
//        customerDTO.setName(customer.getName());
//        customerDTO.setLastName(customerDTO.getLastName());
//        customerDTO.setEmail(customerDTO.getEmail());
//        customerDTO.setPhone(customerDTO.getPhone());
        CustomerDTO customerDTO = new CustomerDTO(customer);
        return customerDTO;
    }

    public void updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = getCustomerById(id);

        //email var mı
        boolean isExistsEmail = customerRepository.existsByEmail(customerDTO.getEmail());
        if (isExistsEmail && !customer.getEmail().equals(customerDTO.getEmail())) {
            throw new ConflictException("Email already exists by  " + customerDTO.getEmail());
        }

        customer.setName(customerDTO.getName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customerRepository.save(customer);

    }

    public Page<Customer> getAllCustomersByPage(Pageable pageable) {
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        return customerPage;
    }


    public List<Customer> getCustomerByName(String name) {
       List <Customer> customers= customerRepository.findByName(name);
       return customers;
    }

    public List<Customer> getCustomerByFullName(String name, String lastName) {
        List <Customer> customers = customerRepository.findByNameAndLastName(name, lastName);
        return customers;
    }

    public List<Customer> getAllCustomerNameLike(String name) {
        String lowerName=name.toLowerCase();
        List <Customer> customers = customerRepository.findAllByNameLike(lowerName);
        return customers;
    }
}
