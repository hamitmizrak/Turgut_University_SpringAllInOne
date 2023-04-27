package com.hamitmizrak.controller.mvc;
import com.hamitmizrak.business.dto.CustomerDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
public interface ICustomerMvc {

    // CREATE => GET
    // http://localhost:2222/customer/create
    String customerCreateGet(Model model);

    // CREATE => POST
    String customerCreatePost( CustomerDto customerDto, BindingResult bindingResult, Model model);

    // LIST
    // http://localhost:2222/customer/list
    String customerListGet(Model model);

    // FIND
    // http://localhost:2222/customer/find/1
    String customerFindGet( Long id, Model model);

    // DELETE
    // http://localhost:2222/customer/delete/1
    String customerDeleteGet( Long id, Model model);

    // UPDATE
    // http://localhost:2222/customer/update/1
    String customerUpdateGet( Long id, Model model);

    // UPDATE
    // http://localhost:2222/customer/update/1
    String customerUpdatePost( Long id, CustomerDto customerDto, BindingResult bindingResult, Model model);
}
