package com.hamitmizrak.business.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2

// CustomerDto
// Validation
public class CustomerDto implements Serializable {
    //Serileştirme
    public static final Long serialVersionUID = 1L;

    //ID
    private Long id;

    //NAME
    @NotEmpty(message = "{customer.name.validation.constraints.NotNull.message}")
    private String name;

    //SURNAME
    @NotEmpty(message = "{customer.surname.validation.constraints.NotNull.message}")
    private String surname;

    //EMAİL
    @NotEmpty(message = "{customer.email.validation.constraints.NotNull.message}")
    @Email(message = "{customer.email.validation.regex.constraints.NotNull.message}")
    // Anonation
    private String email;

    //PASSWORD
    @NotEmpty(message = "{customer.password.validation.constraints.NotNull.message}")
    @Size(min = 7, max = 200, message = "{customer.password.pattern.validation.constraints.NotNull.message}")
    //@Pattern(regexp = "",message = "{}")
    private String password;

    //DATE
    private Date createdDate;
} // end class
