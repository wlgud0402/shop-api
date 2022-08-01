package com.proj.shop.model.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Data
public class Category extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
