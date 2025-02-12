package com.backend.pos.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.backend.pos.entity.Customer;
import com.backend.pos.entity.Staff;

@Entity
@Setter
@Getter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = true)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer = null;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;

    @Column(nullable = true)
    private String remark = null;

    @Column(nullable = false, name = "discount")
    private BigDecimal discount = BigDecimal.ZERO;

    @Column(nullable = false, name = "is_paid")
    private Boolean isPaid = Boolean.FALSE;

    @Column(nullable = false)
    private Boolean isActive = Boolean.TRUE;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
