package com.backend.pos.entity;


import jakarta.persistence.*;

import com.backend.pos.entity.BasePersonEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "Staff")
public class Staff extends BasePersonEntity {
    public Staff() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Column(unique = true, nullable = false)
    private String username;

    @Setter
    private String password;

    @Setter
    @Column(name = "is_admin")
    private boolean isAdmin = false;

    @Setter
    @Column(name = "is_supervisor")
    private boolean isSupervisor = false;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    public void setLastLogin() {
        this.lastLogin = LocalDateTime.now();
    }
}
