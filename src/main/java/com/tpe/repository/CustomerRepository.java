package com.tpe.repository;

import com.tpe.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {


    boolean existsByEmail(String email);

    List<Customer> findByName(String name);

    List<Customer> findByNameAndLastName(String name,String lastName);
}
