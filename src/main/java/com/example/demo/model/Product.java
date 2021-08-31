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

    @Column(name = "user_id")
    public Long mUserId;
    @Column(name = "category_id")
    public Long mCategoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CURRENCY")
    User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mId;
    @Column(name = "created_date", nullable = false)
    private Date mCreatedDate;
    @Column(name = "active_date", nullable = false)
    private Date mActiveDate;
    private String mName;
    private String mDescription;
    private Long mPrice;
    private String mPayLoad;

}
