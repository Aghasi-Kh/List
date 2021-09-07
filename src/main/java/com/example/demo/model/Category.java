package com.example.demo.model;

import com.example.demo.dto.CategoryFilter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long mId;

    @Column(name = "name", nullable = false)
    private String mName;

    @Type(type = "jsonb")
    @Column(name = "filter", nullable = false)
    private CategoryFilter mFilter;

    @Column(name = "parent_id", nullable = false)
    private long mParent_Id;


}
