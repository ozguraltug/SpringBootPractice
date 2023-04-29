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


    //ODEV2:
    //5-id ile customer getirme->http://localhost:8080/customers/Custom?id=1

    @GetMapping("/custom") //önce dto suz yap sonra dto yaz
    public ResponseEntity<CustomerDTO> getCustomerDTOById2(@RequestParam("id") Long id) {
        CustomerDTO customerDTO = customerService.getCustomerDTOById(id);
        return ResponseEntity.ok(customerDTO);
    }
    //ODEV2:
    // 6-id ile customer silme->http://localhost:8080/customers/custom?id=1
    //Customer is deleted successfully mesajı dönsün

    @DeleteMapping("/custom")
    public ResponseEntity<String> deleteCustomerById(@RequestParam("id") Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok("Customer is deleted successfully");
    }

    //task 7: id ile customerı update etme->http://localhost:8080/customers/update/1
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok("Customer is updated successfully");
    }

    //paginated list
    //6-Tüm customerları page page getirme->http://localhost:8080/customers/page=1&size=2&sort=id&direction=asc
    @GetMapping("/page")
    public ResponseEntity<Page<Customer>> getAllCustomersPage(@RequestParam("page") int page, //hangi sayfa
                                                              @RequestParam("size") int size, //bir sayfada kaç adet
                                                              @RequestParam("sort") String prop, //sıralama fieldı burada id
                                                              @RequestParam("direction") Sort.Direction direction) //asc dsc
    {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));
        Page<Customer> customerPage = customerService.getAllCustomersByPage(pageable);
        return ResponseEntity.ok(customerPage);
    }

    //7.Name bilgisi ile customer getirme->http://localhost:8080/customers/query?name=Jack
    @GetMapping("/query")
    public ResponseEntity<List<Customer>> getCustomerByName(@RequestParam("name") String name) {
        List<Customer> customerList = customerService.getCustomerByName(name);
        return ResponseEntity.ok(customerList);
    }

    //8.Name ve lastName bilgisi ile customer getirme->http://localhost:8080/customers/fullquery?name=Jack&lastName=Sparrow
    @GetMapping("/fullquery")
    public ResponseEntity<List<Customer>> getCustomerByFullName(@RequestParam("name") String name,
                                                                @RequestParam("lastName") String lastName) {
        List<Customer> customerList = customerService.getCustomerByFullName(name, lastName);
        return ResponseEntity.ok(customerList);
    }

    //9.İsmi ... içeren customer ları getirme->http://localhost:8080/customers/jpql?name=Ja
    @GetMapping("/jpql")
    public ResponseEntity<List<Customer>> getAllCustomerNameLike(@RequestParam("name") String name) {
        List<Customer> customerList = customerService.getAllCustomerNameLike(name);
        return ResponseEntity.ok(customerList);
    }

    //Ödev:Requestle gelen kelime name veya lastname de geçen customerları getir ->http://localhost:8080/customers/search?word=Ja


}