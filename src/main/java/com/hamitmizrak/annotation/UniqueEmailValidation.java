package com.hamitmizrak.annotation;

import com.hamitmizrak.data.entity.CustomerEntity;
import com.hamitmizrak.data.repository.ICustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

// LOMBOK
@RequiredArgsConstructor
public class UniqueEmailValidation implements ConstraintValidator<CustomerUniqueEmail, String> {

    //1.YOL => Field Injection
    //@Autowired
    //IUserRegisterRepository iUserRegisterRepository;

    //2.YOL ==> Constructor Injection
   /* public UniqueEmailValidation(IUserRegisterRepository iUserRegisterRepository) {
        this.iUserRegisterRepository = iUserRegisterRepository;
    }*/

    //3.YOL ==> Lombok Injection
   private final ICustomerRepository iCustomerRepository;

    //Email addresinde database böyle bir email var mı yok mu
    public boolean isValid(String email, ConstraintValidatorContext context) {
        CustomerEntity customerEntity = iCustomerRepository.findByEmail(email);
        //eğer bu email database varsa return false
        if (customerEntity != null){
            return false;
        }
        return true;
    }
}
