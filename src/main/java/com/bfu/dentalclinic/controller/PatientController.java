package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.controller.payload.patient.NewPatientPayload;
import com.bfu.dentalclinic.controller.payload.patient.UpdatePatientPayload;
import com.bfu.dentalclinic.entity.Patient;
import com.bfu.dentalclinic.repository.PatientRepository;
import com.bfu.dentalclinic.sevice.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("hospital/patient")
public class PatientController {

    private PatientService patientService;

    @GetMapping("list")
    public String getAllPatients(@RequestParam(required = false) String search, Model model) {
        List<Patient> patients;
        if (search != null && !search.isEmpty()) {
            patients = patientService.searchPatients(search);
        } else {
            patients = patientService.getAllPatients();
        }
        model.addAttribute("patients", patients);
        return "patient/list";
    }

    @GetMapping("/list/{id}")
    public String detailPatient(@PathVariable Long id,
                                Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        return "patient/detail";
    }

    @PostMapping("create-patient")
    public String createPatient(NewPatientPayload payload) {
        patientService.createPatient(
                payload.firstName(),
                payload.lastName(),
                payload.dateOfBirth(),
                payload.phoneNumber());
        return "redirect:/hospital/patient/list";
    }

    @PostMapping("/update-patient/{id}")
    public String updatePatient(@PathVariable Long id, UpdatePatientPayload payload) {
        patientService.updatePatient(id,
                payload.firstName(),
                payload.lastName(),
                payload.dateOfBirth(),
                payload.phoneNumber());
        return "redirect:/hospital/patient/list/" + id;
    }

    @PostMapping("/{id}")
    public String deletePatientById(@PathVariable Long id) {
        patientService.deletePatientById(id);
        return "redirect:/hospital/patient/list";
    }

    @PostMapping("/delete-patients")
    public String deleteAllPatients() {
        patientService.deleteAllPatients();
        return "redirect:/hospital/patient/list";
    }
}