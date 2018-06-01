package com.javaee.tests.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javaee.tests.domain.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>{

}
