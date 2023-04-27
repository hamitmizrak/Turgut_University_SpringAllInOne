package com.hamitmizrak.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
    @Column( columnDefinition = "varchar(255) default 'picture.png'")
    private String image;

    // Javada olsun ancak Database olmasın
    @Transient
    private Object specialObject;

    // EMAİL
    // virgüllü sayı: columnDefinition = "Decimal(10,2) default '100.00'")
    @Column(
            name = "email",
            nullable = true,
            //unique = true, // bunun yerine kendi Annotation yazdım
            length = 100,
            insertable = true,
            updatable = true,
            columnDefinition = "varchar(255) default 'email yazmadiniz'")
    private String email;
}
