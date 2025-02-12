package com.backend.pos.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import com.backend.pos.entity.BasePersonEntity;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Customer extends BasePersonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    @Column(name = "membered_at", updatable = false)
    private LocalDateTime memberedAt;

    @Column(name = "last_order", nullable = true)
    private LocalDateTime lastOrder = null;

}
