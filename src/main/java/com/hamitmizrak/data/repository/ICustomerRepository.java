package com.hamitmizrak.data.repository;

import com.hamitmizrak.data.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository  extends CrudRepository<CustomerEntity,Long> {

    //Delivered Query
    CustomerEntity findByEmail(String email);
}
