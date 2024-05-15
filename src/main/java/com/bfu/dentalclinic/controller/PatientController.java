package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.controller.payload.NewPatientPayload;
import com.bfu.dentalclinic.controller.payload.UpdatePatientPayload;
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

    @GetMapping("/list/{id}")
    public String detailPatient(@PathVariable Long id,
                                Model model) {
        model.addAttribute("patient", patientRepository.getPatientById(id));
        return "patient/detail";
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

    @PostMapping("/update-patient/{id}")
    public String updatePatient(@PathVariable Long id, UpdatePatientPayload payload) {
        patientRepository.updatePatient(id,
                payload.firstName(),
                payload.lastName(),
                payload.dateOfBirth(),
                payload.phoneNumber());
        return "redirect:/hospital/patient/list/" + id;
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