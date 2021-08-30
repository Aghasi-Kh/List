package com.example.demo.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mId;

    @Column(name = "created_date")
    private Date mCreatedDate;

    @Column(name = "active_date")
    private Date mActiveDate;

    private String mName;

    private String mDescription;

    private Long mPrise;

    private String mPayLoad;

    @Column(name = "user_id")
    public Long mUserId;

    @Column(name = "category_id")
    public Long mCategoryId;


}
