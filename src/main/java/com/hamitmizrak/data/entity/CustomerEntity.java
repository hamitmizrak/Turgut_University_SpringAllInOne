package com.hamitmizrak.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "customer")
public class CustomerEntity  extends BaseEntity implements Serializable  {
    // Serileştirme
    public static final Long serialVersionUID = 1L;

    // NAME
    private String name;

    // SURNAME
    private String surname;

    // PASSWORD
    private String password;

    // LOB : büyük veriler
    @Lob
    private String image;

    // Javada olsun ancak Database olmasın
    @Transient
    private Object specialObject;


    // EMAİL
    // virgüllü sayı: columnDefinition = "Decimal(10,2) default '100.00'")
    @Column(
            name = "email",
            nullable = true,
            unique = true,
            length = 100,
            insertable = true,
            updatable = true,
            columnDefinition = "varchar(255) default 'email yazmadiniz'")
    private String email;
}
