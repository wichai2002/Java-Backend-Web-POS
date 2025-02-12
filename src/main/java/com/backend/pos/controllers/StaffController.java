package com.backend.pos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;
import com.backend.pos.entity.Staff;
import com.backend.pos.services.StaffService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/")
    public ResponseEntity<List<Staff>> GetAllStaff() {
        try {
            List<Staff> staff = staffService.findAll();

            if (staff.isEmpty()) {
                return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
            }

            return new ResponseEntity<>(staff, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> GetStaffById(@PathVariable int id) {
        try{
            Optional<Staff> staff = staffService.findById(id);
            if (staff.isPresent()) {
                return new ResponseEntity<>(staff.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Staff> PostStaff(@RequestBody Staff staff) {
        try {
            Staff savedStaff = staffService.save(staff);
            return new ResponseEntity<Staff>(savedStaff, HttpStatus.CREATED);
        } catch (Exception e) {
             return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Staff> PutStaff(@PathVariable int id, @RequestBody Staff staff) {
        try{
            Optional<Staff> staffOptional = staffService.findById(id);

            if (staffOptional.isPresent()) {
                Staff getStaff = staffOptional.get();

                // Copy properties from input staff to existing staff
                BeanUtils.copyProperties(staff, getStaff, "id", "password");

                staffService.update(getStaff);

                return new ResponseEntity<>(getStaff, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Staff> DeleteStaff(@PathVariable int id) {
        try{
            Optional<Staff> staffOptional = staffService.findById(id);
            if (staffOptional.isPresent()) {
                staffService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
