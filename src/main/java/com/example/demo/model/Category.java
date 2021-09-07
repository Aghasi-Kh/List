package com.example.demo.model;

import com.example.demo.dto.CategoryFilter;
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

    @Column(name = "name", nullable = false)
    private String mName;

    @Column(name = "filter", nullable = false)
    private CategoryFilter mFilter;

    @Column(name = "parent_id", nullable = false)
    private long mParent_Id;


}
