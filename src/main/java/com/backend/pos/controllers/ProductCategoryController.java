package com.backend.pos.controllers;

import org.springframework.beans.BeanUtils;
import com.backend.pos.entity.ProductCategory;
import com.backend.pos.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/product-category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/")
    public ResponseEntity<List<ProductCategory>> getAllProductCategory() {
        try{
            List<ProductCategory> productCategories = productCategoryService.findAll();

            if (productCategories.isEmpty()) {
                return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
            }

            return new ResponseEntity<>(productCategories, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable Integer id) {
        try{
            Optional<ProductCategory> productCategory = productCategoryService.findById(id);

            if (productCategory.isPresent()) {
                return new ResponseEntity<>(productCategory.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) {
        try{
            ProductCategory productCategoryCreated = productCategoryService.save(productCategory);
            return new ResponseEntity<>(productCategoryCreated, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/")
    public ResponseEntity<ProductCategory> updateProductCategory(@PathVariable Integer id, @RequestBody ProductCategory productCategory) {
        try{
            Optional<ProductCategory> productCategoryOptional = productCategoryService.findById(id);
            if (productCategoryOptional.isPresent()) {

                ProductCategory productCategoryUpdating = productCategoryOptional.get();
                BeanUtils.copyProperties(productCategory, productCategoryUpdating);

                productCategoryService.save(productCategoryUpdating);

                return new ResponseEntity<>(productCategoryUpdating, HttpStatus.OK);
            }

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<ProductCategory> deleteProductCategory(@PathVariable Integer id) {
        Optional<ProductCategory> productCategoryOptional = productCategoryService.findById(id);

        if (productCategoryOptional.isPresent()) {
            productCategoryService.deleteById(id);

            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
