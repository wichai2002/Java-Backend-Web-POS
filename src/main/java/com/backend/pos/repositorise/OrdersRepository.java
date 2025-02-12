package com.backend.pos.repositorise;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.pos.entity.Order;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {

}
