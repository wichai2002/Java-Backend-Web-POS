package com.backend.pos.services;

import com.backend.pos.entity.Staff;
import com.backend.pos.repositorise.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.pos.utils.HashUtil;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    private final HashUtil hashUtil = new HashUtil();

    public Staff save(Staff staff) {
        String password = staff.getPassword();

        if (!password.isEmpty()) {
            String hashedPassword = hashUtil.hasPassword(password);
            staff.setPassword(hashedPassword);
        }
        return staffRepository.save(staff);
    }

    public Staff changePassword(Staff staff, String password) {
        String hashedPassword = hashUtil.hasPassword(password);
        staff.setPassword(hashedPassword);
        return staffRepository.save(staff);
    }

    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    public Optional<Staff> findById(Integer id) {
        return staffRepository.findById(id);
    }

    public void deleteById(Integer id) {
        staffRepository.deleteById(id);
    }

    public void update(Staff staff) {
        staffRepository.save(staff);
    }

}
