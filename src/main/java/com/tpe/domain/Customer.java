package com.tpe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @GeneratedValue annotation'ı, birincil anahtar alanının değerinin
    // nasıl oluşturulacağını belirlemek için kullanılır.
    // Örneğin, @GeneratedValue(strategy = GenerationType.IDENTITY)
    // kullanılarak, birincil anahtar alanı bir kimlik sütunu
    // (identity column) olarak yapılandırılmış bir veritabanında
    // otomatik olarak oluşturulur.
    private Long id;

    @NotNull(message = "First name can not be null")//null olamaz ama "" olabilir veya " " olabilir
    @NotBlank(message = "First name can not be space")//null olmaz empty olamaz boşluk olamaz
    @NotEmpty //null olamaz empty olamaz boşluk olabilir
    @Size(min = 2, max = 50)
    private String name;
    @NotNull(message = "Last name can not be null")
    @NotBlank(message = "Last name can not be space")
    @Size(min = 2, max = 50)
    private String lastName;

    @Email //...@...
    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<Product> products=new ArrayList<>();



}