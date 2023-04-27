package com.hamitmizrak.controller.mvc;
import com.hamitmizrak.business.dto.CustomerDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface ICustomerMvc {

    // CREATE => GET
    // http://localhost:2222/controller/create
    String createGetMapping(Model model);

    // CREATE => POST
    String createPostMapping( CustomerDto customerDto, BindingResult bindingResult, Model model);

    // LIST
    // http://localhost:2222/controller/list
     String listGetMapping(Model model);

    // FIND
    // http://localhost:2222/controller/find/1
    String findGetMapping(Long id, Model model);

    // DELETE
    // http://localhost:2222/controller/delete/1
    String deleteGetMapping( Long id, Model model);

    // UPDATE => GET
    // http://localhost:2222/controller/update/1
    String updateGetMapping( Long id, Model model);

    // UPDATE => POST
    String updatePostMapping(Long id, CustomerDto customerDto, BindingResult bindingResult, Model model);
}
