package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.business.dto.CustomerDto;
import com.hamitmizrak.business.services.ICustomerServices;
import com.hamitmizrak.data.entity.CustomerEntity;
import com.hamitmizrak.data.repository.ICustomerRepository;
import com.hamitmizrak.exception.HamitMizrakException;
import com.hamitmizrak.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LOMBOK
@RequiredArgsConstructor // injection
@Log4j2
//@SneakyThrows //throws

@Service
// Asıl iş yükünü yapan class
public class CustomerServices implements ICustomerServices {

    // INJECTION
    private final ICustomerRepository iCustomerRepository;
    private final ModelMapperBean modelMapperBean;
    private final PasswordEncoderBean passwordEncoderBean;


    //MODEL MAPPER
    @Override
    public CustomerDto EntityToDto(CustomerEntity customerEntity) {
        return modelMapperBean.modelMapperMethod().map(customerEntity, CustomerDto.class);
    }

    @Override
    public CustomerEntity DtoToEntity(CustomerDto customerDto) {
        return modelMapperBean.modelMapperMethod().map(customerDto, CustomerEntity.class);
    }


    // CREATE
    @Override
    @Transactional // create,delete,update
    public CustomerDto createRegister(CustomerDto customerDto) {
        if (customerDto != null) {
            // Passwork Masking
            customerDto.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(customerDto.getPassword()));
            // DTO ==> ENTITY Model Mapper
            CustomerEntity customerEntityMapper = DtoToEntity(customerDto);
            CustomerEntity customerEntityRepository = iCustomerRepository.save(customerEntityMapper);
            // Kaydedilen verinin ID dönsün
            customerDto.setId(customerEntityRepository.getId());
            customerDto.setCreatedDate(customerEntityRepository.getCreatedDate());
        } else if (customerDto == null) {
            throw new HamitMizrakException("Customer Dto Null geldi");
        }
        return customerDto;
    }


    // LIST
    @Override
    public List<CustomerDto> getAllCustomers() {
        Iterable<CustomerEntity> customerEntityList = iCustomerRepository.findAll();
        // EntityList ==> DtoList çevir
        List<CustomerDto> entityToDtoList = new ArrayList<>();
        for (CustomerEntity temp : customerEntityList) {
            CustomerDto customerDto = EntityToDto(temp);
            entityToDtoList.add(customerDto);
        }
        return entityToDtoList;
    }

    // FIND
    @Override
    public CustomerDto getFindByCustomerId(Long id) {
        CustomerEntity customerEntity=null;
        if(id!=null){
            customerEntity = iCustomerRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id + " nolu ID yoktur"));
        }else if (id == null) {
            throw new HamitMizrakException("Customer Dto Null geldi");
        }
        return EntityToDto(customerEntity);
    }

    // DELETE
    @Override
    @Transactional // create,delete,update
    public Map<String, Boolean> deleteCustomer(Long id) {
        Map<String, Boolean> mapDelete = new HashMap<>();
        // FIND
        CustomerDto customerDto = getFindByCustomerId(id);
        // Dto ==> Entity
        CustomerEntity customerEntity = DtoToEntity(customerDto);
        if (customerEntity != null) {
            iCustomerRepository.delete(customerEntity);
            mapDelete.put("Silindi",Boolean.TRUE);
        }
        return mapDelete;
    }

    // UPDATE
    @Override
    @Transactional // create,delete,update
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        // FIND
        CustomerDto customerDtoFind = getFindByCustomerId(id);
        // Dto ==> Entity
        CustomerEntity customerEntity = DtoToEntity(customerDtoFind);
        if (customerEntity != null) {
            customerEntity.setName(customerDto.getName());
            customerEntity.setSurname(customerDto.getSurname());
            customerEntity.setEmail(customerDto.getEmail());
            customerEntity.setImage(customerDto.getImage());
            customerEntity.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(customerDto.getPassword()));
            iCustomerRepository.save(customerEntity);
            // Kaydedilen verinin ID dönsün
            customerDto.setId(customerEntity.getId());
            customerDto.setCreatedDate(customerEntity.getCreatedDate());
        }
        return customerDto;
    }
} //end class
