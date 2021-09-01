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

    @Column(name = "username", nullable = false)
    @Size(min = 2, max = 15)
    private String mUserName;

    @Column(name = "firstname", nullable = false)
    @Size(min = 2, max = 25)
    private String mFirstName;

    @Column(name = "lastname", nullable = false)
    @Size(min = 2, max = 25)
    private String mLastName;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String mEmail;

    @Column(name = "phone_number", nullable = false, unique = true)
    private Long mPhoneNumber;

    @Column(name = "password", nullable = false, unique = true)
    private String mPassword;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;


}
