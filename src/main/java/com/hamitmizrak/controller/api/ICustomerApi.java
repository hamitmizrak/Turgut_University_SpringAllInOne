package com.hamitmizrak.controller.api;

import com.hamitmizrak.business.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface ICustomerApi {

    //SPEED DATA
    public ResponseEntity<CustomerDto>  speedData();

    // DELETE ALL
    public ResponseEntity<String>  customerAllDelete();

    // CREATE
    public ResponseEntity<CustomerDto>  createRegister(CustomerDto customerDto);

    // LIST
    public ResponseEntity<List<CustomerDto>>  getAllCustomers();

    // FIND ID
    public ResponseEntity<?>  getFindByCustomerId(Long id);

    // DELETE
    public ResponseEntity<Map<String,Boolean>>  deleteCustomer(Long id);

    // UPDATE
    public ResponseEntity<CustomerDto> updateCustomer(Long id, CustomerDto customerDto);
}
