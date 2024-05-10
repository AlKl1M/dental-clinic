package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.controller.payload.NewPatientPayload;
import com.bfu.dentalclinic.entity.Patient;
import com.bfu.dentalclinic.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("hospital/patient")
public class PatientController {

    private PatientRepository patientRepository;

    @GetMapping("list")
    public String getAllPatients(Model model) {
        model.addAttribute("patients", this.patientRepository.getAllPatients());
        return "patient/list";
    }

    @PostMapping("create-patient")
    public String createPatient(NewPatientPayload payload) {
        patientRepository.createPatient(
                payload.firstName(),
                payload.lastName(),
                payload.dateOfBirth(),
                payload.phoneNumber());
        return "redirect:/hospital/patient/list";
    }

    @PutMapping("/{id}")
    public void updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        patient.setId(id);
        patientRepository.updatePatient(patient);
    }

    @PostMapping("/{id}")
    public String deletePatientById(@PathVariable Long id) {
        patientRepository.deletePatientById(id);
        return "redirect:/hospital/patient/list";
    }

    @PostMapping("/delete-patients")
    public String deleteAllPatients() {
        patientRepository.deleteAllPatients();
        return "redirect:/hospital/patient/list";
    }
}