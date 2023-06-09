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
@RequestMapping("/customer")
public class CustomerMvcImpl implements ICustomerMvc {

    // List,Create, Delete => gelen modelAddAttributes
    private String modelAttributesTemp=null;

    // Injection
    private final ICustomerServices customerServices;

    // SPEEAD DATA => GET
    // http://localhost:2222/customer/speedData
    @Override
    @GetMapping("/speedData")
    public String customerSpeedDataGet() {
        customerServices.speedData();
        modelAttributesTemp="5 tane veri eklendi";
        return "redirect:/customer/list";
    }

    // ALL DELETE => GET
    // http://localhost:2222/customer/customerAllData
    @Override
    @GetMapping("/customerAllData")
    public String customerAllDeleteGet() {
        customerServices.customerAllDelete();
        modelAttributesTemp="Bütün Veriler Silindi";
        return "redirect:/customer/list";
    }

    // CREATE => GET
    // http://localhost:2222/customer/create
    @Override
    @GetMapping("/create")
    public String customerCreateGet(Model model) {
        model.addAttribute("customer_create", new CustomerDto());
        return "customer/create";
    }

    // CREATE => POST
    @Override
    @PostMapping("/create")
    public String customerCreatePost(@Valid @ModelAttribute("customer_create") CustomerDto customerDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("HATA CREATED" + bindingResult);
            return "customer/create";
        }
        //eğer hata yoksa
        model.addAttribute("success_create", "Müşteri Eklendi" + customerDto);
        log.info(customerDto);
        // Services Create
        customerServices.createRegister(customerDto);
        modelAttributesTemp="Eklendi "+customerDto;
        return "redirect:/customer/list";
    }


    // LIST
    // http://localhost:2222/customer/list
    @Override
    @GetMapping("/list")
    public String customerListGet(Model model) {
        // Services List
        List<CustomerDto> list = customerServices.getAllCustomers();
        model.addAttribute("customer_list", list);
        list.forEach((temp) -> {
            System.out.println(temp);
        });
        model.addAttribute("model_list",modelAttributesTemp);
        return "customer/list";
    }

    // FIND
    // http://localhost:2222/customer/find/1
    @Override
    @GetMapping("/find/{id}")
    public String customerFindGet(@PathVariable(name = "id") Long id, Model model) {
        // Services List
        CustomerDto find = customerServices.getFindByCustomerId(id);
        model.addAttribute("customer_find", find);
        System.out.println(find);
        return "customer/detail";
    }

    // DELETE
    // http://localhost:2222/customer/delete/1
    @Override
    @GetMapping("/delete/{id}")
    public String customerDeleteGet(@PathVariable(name = "id") Long id, Model model) {
        // Services List
        Map<String, Boolean> mapDelete = customerServices.deleteCustomer(id);
        model.addAttribute("customer_find", mapDelete);
        // Ekranda Göster
        modelAttributesTemp="Silindi";
        System.out.println(mapDelete);
        //return "redirect:/customer/list?sil=tamam";
        return "redirect:/customer/list";
    }


    // UPDATE
    // http://localhost:2222/customer/update/1
    @Override
    @GetMapping("/update/{id}")
    public String customerUpdateGet(@PathVariable(name = "id") Long id, Model model) {
        CustomerDto customerDto = customerServices.getFindByCustomerId(id);
        if (customerDto != null) {
            model.addAttribute("customer_update", customerDto);
        } else
            model.addAttribute("customer_update", id + " numaralı veri yoktur");
        // Ekranda Göster
        modelAttributesTemp=id + " numaralı veri yoktur";
        return "customer/update";
    }

    // UPDATE
    // http://localhost:2222/customer/update/1
    @Override
    @PostMapping("/update/{id}")
    public String customerUpdatePost(
            @PathVariable(name = "id") Long id,
            @Valid @ModelAttribute("customer_update") CustomerDto customerDto,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            log.error("HATA: " + bindingResult);
            return "customer/update";
        }
        customerServices.updateCustomer(id, customerDto);
        model.addAttribute("success_update", "Güncellendi");
        modelAttributesTemp=id + " Güncellendi "+customerDto;
        return "redirect:/customer/list";
    }
}

