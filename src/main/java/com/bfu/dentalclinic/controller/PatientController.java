package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.entity.Patient;
import com.bfu.dentalclinic.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private PatientRepository patientRepository;

    @PostMapping
    public void createPatient(@RequestBody Patient patient) {
        patientRepository.createPatient(patient);
    }

    @PutMapping("/{id}")
    public void updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        patient.setId(id);
        patientRepository.updatePatient(patient);
    }

    @DeleteMapping("/{id}")
    public void deletePatientById(@PathVariable Long id) {
        patientRepository.deletePatientById(id);
    }

    @DeleteMapping
    public void deleteAllPatients() {
        patientRepository.deleteAllPatients();
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.getAllPatients();
    }
}