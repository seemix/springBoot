package com.springboot.models;

import com.springboot.models.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Admin.class)

    private int id;
    @NotEmpty
    @Length(min = 2, message = "The field must be at least 2 characters")
    @JsonView({Views.Admin.class, Views.Client.class})
    private String name;

    @NotEmpty
    @Length(min = 3, message = "The field must be at least 2 characters")
    private String surname;

    @NotEmpty
    @Email(message = "Email is not valid")
    private String email;

    public Customer(String name) {
        this.name = name;
    }

    public Customer(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}


