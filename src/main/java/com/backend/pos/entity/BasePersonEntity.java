package com.backend.pos.entity;

import jakarta.persistence.*;
import com.backend.pos.entity.enumClass.Gender ;
import lombok.Setter;
import lombok.Getter;

import java.util.Date;

@Setter
@Getter
@MappedSuperclass
public abstract class BasePersonEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "birth_date")
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private Gender gender = Gender.NOTSPECIFIED;

    @Column(name = "id_number", unique = true, nullable = true)
    private String idNumber ;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isActive = true;

}
