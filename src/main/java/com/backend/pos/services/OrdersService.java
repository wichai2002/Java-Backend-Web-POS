package com.backend.pos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.pos.entity.Order;
import com.backend.pos.repositorise.OrdersRepository;


@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    public List<Order> getAllOrders() {
        return ordersRepository.findAll();
    }

    public Optional<Order> getOrderById(int id) {
        return ordersRepository.findById(id);
    }

    public Order saveOrder(Order order) {
        return ordersRepository.save(order);
    }

    public void deleteOrderById(int id) {
        ordersRepository.deleteById(id);
    }
}
