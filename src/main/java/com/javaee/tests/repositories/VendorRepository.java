package com.javaee.tests.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javaee.tests.domain.Vendor;

@Repository
public interface VendorRepository extends MongoRepository<Vendor, String>{

}
