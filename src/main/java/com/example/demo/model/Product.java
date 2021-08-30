package com.example.demo.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mId;

    @Column(name = "created_date")
    private Date mCreatedDate;

    @Column(name = "active_date")
    private Date mActiveDate;

    private String name;

    private String description;

    private Long prise;

    private String payLoad;

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "category_id")
    public Long categoryId;


}
