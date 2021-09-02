package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mUserID;

    @Column(name = "first_name", nullable = false)
    @Size(min = 2, max = 25)
    private String mFirstName;

    @Column(name = "last_name", nullable = false)
    @Size(min = 2, max = 25)
    private String mLastName;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String mEmail;

    @Column(name = "phone_number", nullable = false, unique = true)
    private Long mPhoneNumber;

    @Column(name = "password", nullable = false)
    @Size(min = 8)
    private String mPassword;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;


}
