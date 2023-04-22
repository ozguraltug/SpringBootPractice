package com.tpe.controller;

import com.tpe.domain.Customer;
import com.tpe.dto.CustomerDTO;
import com.tpe.service.CustomerService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")

public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //1-Spring boot u selamlama http://localhost:8080/customers/greet
    @GetMapping("/greet")
    public String greetSpringBoot() {
        return "Hello Spring Boot";

    }

    //2-Customer oluşturma/kaydetme http://localhost:8080/customers/save
    @PostMapping("/save")
    public ResponseEntity<String> saveCustomer(@Valid @RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        String message = "Customer saved successfully";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    //3-Tüm customerları getirme->http://localhost:8080/customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customerList = customerService.getAllCustomers();
        return ResponseEntity.ok(customerList);
    }

    //4-Id ile customer getirme->http://localhost:8080/customers/1
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }


    //ODEV2:id ile customer getirme->http://localhost:8080/customers/Custom?id=1

    @GetMapping("/custom")
    public ResponseEntity<CustomerDTO> getCustomerDTOById2(@RequestParam("id") Long id) {
        CustomerDTO customerDTO = customerService.getCustomerDTOById(id);
        return ResponseEntity.ok(customerDTO);
    }
    //ODEV2:id ile customer silme->http://localhost:8080/customers/custom?id=1
    //Customer is deleted successfully mesajı dönsün

    @DeleteMapping("/custom")
    public ResponseEntity<String> deleteCustomerById(@RequestParam("id") Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok("Customer is deleted successfully");
    }

    //task 5: id ile customerı update etme->http://localhost:8080/customers/update/1
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok("Customer is updated successfully");
    }

    //paginated list
    //6-Tüm customerları page page getirme->http://localhost:8080/customers/page=1&size=2&sort=id&direction=asc
    @GetMapping("/page")
    public ResponseEntity<Page<Customer>> getAllCustomersPage(@RequestParam("page") int page,
                                                              @RequestParam("size") int size,
                                                              @RequestParam("sort") String prop,
                                                              @RequestParam("direction") String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction));
        Page<Customer> customerPage = customerService.getAllCustomersByPage(pageable);
        return ResponseEntity.ok(customerPage);


    }
}