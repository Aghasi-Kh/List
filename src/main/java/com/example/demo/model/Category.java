package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long mId;
    @Column(name = "name")
    private String mName;

    @Column(name = "filter")
    private String mFilter;

    @Column(name = "parent_id")
    private long mParent_Id;


}
