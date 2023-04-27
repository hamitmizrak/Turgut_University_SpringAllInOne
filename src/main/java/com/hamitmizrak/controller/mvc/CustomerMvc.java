package com.hamitmizrak.controller.mvc;


import com.hamitmizrak.business.dto.CustomerDto;
import com.hamitmizrak.business.services.ICustomerServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// LOMBOK
@RequiredArgsConstructor // Injection
@Log4j2

// Spring MVC
@Controller
@RequestMapping("/controller")
public class CustomerMvc implements ICustomerMvc {

    // Injection
    private final ICustomerServices customerServices;

    // CREATE => GET
    // http://localhost:2222/controller/create
    @Override
    @GetMapping("/create")
    public String createGetMapping(Model model) {
        model.addAttribute("customer_create", new CustomerDto());
        return "customer/create";
    }

    // CREATE => POST
    @Override
    @PostMapping("/create")
    public String createPostMapping(@Valid @ModelAttribute("customer_create") CustomerDto customerDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("HATA CREATED" + bindingResult);
            return "customer/create";
        }
        //eğer hata yoksa
        model.addAttribute("customer_success", "Müşteri Eklendi" + customerDto);
        log.info(customerDto);
        // Services Create
        customerServices.createRegister(customerDto);
        return "redirect:/list";
    }


    // LIST
    // http://localhost:2222/controller/list
    @Override
    @GetMapping("/list")
    public String listGetMapping(Model model) {
        // Services List
        List<CustomerDto> list = customerServices.getAllCustomers();
        model.addAttribute("customer_list", list);
        list.forEach((temp) -> {
            System.out.println(temp);
        });
        return "customer/list";
    }

    // FIND
    // http://localhost:2222/controller/find/1
    @Override
    @GetMapping("/find/{id}")
    public String findGetMapping(@PathVariable(name = "id") Long id, Model model) {
        // Services List
        CustomerDto find = customerServices.getFindByCustomerId(id);
        model.addAttribute("customer_find", find);
        System.out.println(find);
        return "redirect:/list";
    }

    // DELETE
    // http://localhost:2222/controller/delete/1
    @Override
    @GetMapping("/delete/{id}")
    public String deleteGetMapping(@PathVariable(name = "id") Long id, Model model) {
        // Services List
        Map<String, Boolean> mapDelete = customerServices.deleteCustomer(id);
        model.addAttribute("customer_find", mapDelete);
        System.out.println(mapDelete);
        return "redirect:/list";
    }


    // UPDATE => GET
    // http://localhost:2222/controller/update/1
    @Override
    @GetMapping("/update/{id}")
    public String updateGetMapping(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("customer_update", new CustomerDto());
        return "customer/update";
    }

    // UPDATE => POST
    @Override
    @PostMapping("/update/{id}")
    public String updatePostMapping(@PathVariable(name = "id") Long id, @Valid @ModelAttribute("customer_update") CustomerDto customerDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("HATA CREATED" + bindingResult);
            return "customer/update";
        }
        //eğer hata yoksa
        model.addAttribute("customer_success", "Müşteri Güncellendi" + customerDto);
        log.info(customerDto);
        // Services Create
        customerServices.updateCustomer(id,customerDto);
        return "redirect:/list";
    }

}
