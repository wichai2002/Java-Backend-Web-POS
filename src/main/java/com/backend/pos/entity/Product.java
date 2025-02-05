package com.backend.pos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import com.backend.pos.entity.ProductCategory;

import java.util.ArrayList;
import java.util.Set;

@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String code;

    @Column(nullable = true)
    private String description = null;

    @Column
    private double price;

    @Column
    private int quantity = 0;

    @Column(nullable = true)
    private String image = null;

    @Column(name="is_active")
    private Boolean isActive = true;

    @ManyToMany
    @JoinTable(
            name="product_productcategory",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "productcategory_id")
    )
    private Set<ProductCategory> productCategories;
}
