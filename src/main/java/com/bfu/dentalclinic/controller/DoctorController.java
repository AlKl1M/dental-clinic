package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.entity.Doctor;
import com.bfu.dentalclinic.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @PostMapping
    public void createDoctor(@RequestBody Doctor doctor) {
        doctorRepository.createDoctor(doctor);
    }

    @PutMapping("/{id}")
    public void updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        doctor.setId(id);
        doctorRepository.updateDoctor(doctor);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctorById(@PathVariable Long id) {
        doctorRepository.deleteDoctorById(id);
    }

    @DeleteMapping
    public void deleteAllDoctors() {
        doctorRepository.deleteAllDoctors();
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorRepository.getAllDoctors();
    }
}