package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mId;

    @Column(name = "created_date", nullable = false)
    private Date mCreatedDate;

    @Column(name = "active_date", nullable = false)
    private Date mActiveDate;

    @Column(name = "category_id")
    public Long mCategoryId;

    @Column(name = "product_name")
    private String mName;

    @Column(name = "product_description")
    private String mDescription;

    @Column(name = "product_price")
    private Long mPrice;

    @Column(name = "payload")
    private String mPayLoad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

}
