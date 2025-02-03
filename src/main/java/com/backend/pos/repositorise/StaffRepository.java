package com.backend.pos.repositorise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Entity
import com.backend.pos.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

}
