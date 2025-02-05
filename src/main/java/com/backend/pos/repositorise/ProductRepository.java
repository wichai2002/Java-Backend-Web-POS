package com.backend.pos.repositorise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.pos.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
