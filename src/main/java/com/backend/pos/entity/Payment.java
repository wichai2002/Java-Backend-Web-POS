package com.backend.pos.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import com.backend.pos.entity.Order;
import com.backend.pos.entity.Staff;

import com.backend.pos.entity.enumClass.PaymentMethod;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;

    @Column
    private Double price = 0.0;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod = PaymentMethod.CASH;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = true)
    private String remark = null;

    @Column(name = "is_active")
    private Boolean isActive = true;

}
