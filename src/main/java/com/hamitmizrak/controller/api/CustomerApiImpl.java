package com.hamitmizrak.controller.api;

import com.hamitmizrak.business.dto.CustomerDto;
import com.hamitmizrak.business.services.impl.CustomerServices;
import com.hamitmizrak.controller.ICustomerApi;
import com.hamitmizrak.error.ApiResult;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

// LOMBOK
@Log4j2
@RequiredArgsConstructor // Injection

// Apı
@RestController
@CrossOrigin(origins = "http://localhost:3000")
// CORS: eğer reactta package.json'da proxy yazarsam @CrossOrigin yazmasamda olur
@RequestMapping("/customer/api/v1")
public class CustomerApiImpl implements ICustomerApi {

    // ERROR
    private ApiResult apiResult;

    // @PostConstruct
    @PostConstruct
    public void springData(){
        apiResult=new ApiResult();
    }

    //Injection
    //1.YOL => Field Injection
    /*
    @Autowired
    private CustomerServices customerServices;
    */

    //2.YOL => Constructor Injection
    /*
    private final CustomerServices customerServices;
    @Autowired
    public CustomerApi(CustomerServices customerServices) {
        this.customerServices = customerServices;
    }
    */

    //3.YOL => Constructor Injection (LOMBOK)
    private final CustomerServices customerServices;

    // CREATE
    // http://localhost:2222/customer/api/v1/create
    @Override
    @PostMapping(value="create")
    public ResponseEntity<CustomerDto> createRegister( @Valid @RequestBody CustomerDto customerDto) {
        // return new ResponseEntity<>(adminDto, HttpStatus.OK);
        // return  ResponseEntity.status(HttpStatus.OK).body(adminDto);
        // return  ResponseEntity.status(200).body(adminDto);
        // return  ResponseEntity.ok().body(adminDto);
        return ResponseEntity.ok(customerServices.createRegister(customerDto));
    }

    // LIST
    // http://localhost:2222/customer/api/v1/list
    @Override
    @GetMapping( "list")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerServices.getAllCustomers());
    }

    // FIND
    // http://localhost:2222/customer/api/v1/find
    // http://localhost:2222/customer/api/v1/find/0
    // http://localhost:2222/customer/api/v1/find/-1
    // http://localhost:2222/customer/api/v1/find/1
    @Override
    @GetMapping({"/find","/find/{id}"})
    public ResponseEntity<?> getFindByCustomerId(@PathVariable(name = "id",required = false) Long id) {
        if (id == null) {
            log.error("API => 404 NOT FOUND");
            //return ResponseEntity.notFound().build();
        } else if (id == 0) {
            log.error("API => 400 BAD REQUEST");
            apiResult = new ApiResult(400,"localhost:2222/customer/api/v1/register/0","Kötü istek","Bad Request");
            //return ResponseEntity.badRequest().build();
            return ResponseEntity.badRequest().body(apiResult);
        } else if (id < 0) {
            log.error("API => 401 UNAUTHROZED");
            apiResult = ApiResult.builder()
                    .error("unAuthorized")
                    .message("Yetkisiz Giriş")
                    .path("localhost:2222/customer/api/v1/register/-1")
                    .status(401)
                    .build();
            return ResponseEntity.status(401).body(apiResult);
        }
        System.out.println(customerServices.getFindByCustomerId(id));
        return ResponseEntity.ok(customerServices.getFindByCustomerId(id));
    }

    // DELETE
    // http://localhost:2222/customer/api/v1/delete/1
    @Override
    @DeleteMapping({"/delete","/delete/{id}"})
    public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable(name = "id", required = false) Long id) {
        return ResponseEntity.ok(customerServices.deleteCustomer(id));
    }

    // UPDATE
    // http://localhost:2222/customer/api/v1/update/1
    @Override
    @PutMapping({"/update","/update/{id}"})
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable(name = "id", required = false) Long id, @Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerServices.updateCustomer(id, customerDto));
    }
} // end update
