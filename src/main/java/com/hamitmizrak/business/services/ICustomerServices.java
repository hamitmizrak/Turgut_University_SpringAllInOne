package com.hamitmizrak.business.services;

import com.hamitmizrak.business.dto.CustomerDto;
import com.hamitmizrak.data.entity.CustomerEntity;

import java.util.List;
import java.util.Map;

public interface ICustomerServices {

    // MODEL MAPPER
    public CustomerDto EntityToDto(CustomerEntity customerEntity  );
    public CustomerEntity DtoToEntity(CustomerDto customerDto);

    // CREATE
    public CustomerDto createRegister(CustomerDto customerDto);

    // LIST
    public List<CustomerDto> getAllCustomers();

    // FIND ID
    public CustomerDto getFindByCustomerId(Long id);

    // DELETE
    public Map<String,Boolean> deleteCustomer(Long id);

    // UPDATE
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto);
}
