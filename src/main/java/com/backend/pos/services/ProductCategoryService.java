package com.backend.pos.services;


import com.backend.pos.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.pos.repositorise.ProductCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    public Optional<ProductCategory> findById(Integer id) {
        return productCategoryRepository.findById(id);
    }

    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    public void deleteById(Integer id) {
        productCategoryRepository.deleteById(id);
    }

}
