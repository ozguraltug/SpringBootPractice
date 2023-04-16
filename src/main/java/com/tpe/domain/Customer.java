package com.tpe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="t_customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="First name can not be null")//null olamaz ama "" olabilir veya " " olabilir
    @NotBlank(message="First name can not be space")//null olmaz empty olamaz boşluk olamaz
    @NotEmpty //null olamaz empty olamaz boşluk olabilir
   @Size(min=2, max=50)
    private String name;
    @NotNull(message="Las name can not be null")
    @NotBlank(message="Last name can not be space")
    @Size(min=2, max=50)
    private String lastName;

    @Email //...@...
    @Column (unique=true, nullable=false)
    private String email;
    private String phone;

}
