package com.example.demo.model;

//import com.github.ankurpathak.password.bean.constraints.ContainDigit;
//import com.github.ankurpathak.password.bean.constraints.ContainLowercase;
//import com.github.ankurpathak.password.bean.constraints.ContainUppercase;
//import com.github.ankurpathak.password.bean.constraints.NotContainWhitespace;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(name = "username", nullable = false)
    @Size(min = 2, max = 15)
    private String mUserName;

    @Column(name = "lastname", nullable = false)
    @Size(min = 2, max = 25)
    private String mLastName;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String mEmail;

    @Column(name = "phone_number", nullable = false, unique = true)
    private Long mPhoneNumber;

    @Column(name = "password", nullable = false, unique = true)
    @Size(min = 8)
//    @NotContainWhitespace
//    @ContainDigit
//    @ContainLowercase
//    @ContainUppercase
    private String mPassword;

    public User(String userName, String lastName, String email, Long phoneNumber, String password) {
        this.mUserName = userName;
        this.mLastName = lastName;
        this.mEmail = email;
        this.mPhoneNumber = phoneNumber;
        this.mPassword = password;
    }


}
