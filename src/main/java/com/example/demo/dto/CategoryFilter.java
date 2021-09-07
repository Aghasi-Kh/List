package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryFilter implements Serializable {
private final static long serialVersionUID = 7702L;
    private String name;
    private String filter;
    private Long parentId;
}