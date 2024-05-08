package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.controller.payload.AppointmentDTO;
import com.bfu.dentalclinic.controller.payload.NewAppointmentPayload;
import com.bfu.dentalclinic.entity.DoctorAppointment;
import com.bfu.dentalclinic.repository.DoctorAppointmentRepository;
import com.bfu.dentalclinic.repository.DoctorRepository;
import com.bfu.dentalclinic.repository.PatientRepository;
import com.bfu.dentalclinic.repository.ReasonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("hospital/appointments")
@AllArgsConstructor
public class DoctorAppointmentController {
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ReasonRepository reasonRepository;
    @GetMapping("list")
    public String getAllAppointments(Model model) {
        model.addAttribute("appointments", doctorAppointmentRepository.getAllDoctorAppointments());
        model.addAttribute("doctors", doctorRepository.getAllDoctors());
        model.addAttribute("patients", patientRepository.getAllPatients());
        model.addAttribute("reasons", reasonRepository.findAll());
        return "appointment/list";
    }

    @GetMapping("admin")
    public String getAllAppointmentsAdmin(Model model) {
        model.addAttribute("appointments", doctorAppointmentRepository.getAllDoctorAppointments());
        model.addAttribute("doctors", doctorRepository.getAllDoctors());
        model.addAttribute("patients", patientRepository.getAllPatients());
        model.addAttribute("reasons", reasonRepository.findAll());
        return "appointment/admin-appointment";
    }

    @PostMapping("create-appointment")
    public String createDoctorAppointment(NewAppointmentPayload doctorAppointment) {
        System.out.println(doctorAppointment.reason_id());
        doctorAppointmentRepository.createDoctorAppointment(doctorAppointment);
        return "redirect:/hospital/appointments/admin";
    }

    @PutMapping
    public void updateDoctorAppointment(@RequestBody DoctorAppointment doctorAppointment) {
        doctorAppointmentRepository.updateDoctorAppointment(doctorAppointment);
    }

    @PostMapping("/{id}")
    public String deleteDoctorAppointmentById(@PathVariable Long id) {
        doctorAppointmentRepository.deleteDoctorAppointmentById(id);
        return "redirect:/hospital/appointments/admin";
    }

    @DeleteMapping
    public void deleteAllDoctorAppointments() {
        doctorAppointmentRepository.deleteAllDoctorAppointments();
    }
}