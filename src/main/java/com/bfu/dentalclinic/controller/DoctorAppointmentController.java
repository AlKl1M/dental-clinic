package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.entity.DoctorAppointment;
import com.bfu.dentalclinic.repository.DoctorAppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor-appointments")
@AllArgsConstructor
public class DoctorAppointmentController {

    private final DoctorAppointmentRepository doctorAppointmentRepository;

    @PostMapping
    public void createDoctorAppointment(@RequestBody DoctorAppointment doctorAppointment) {
        doctorAppointmentRepository.createDoctorAppointment(doctorAppointment);
    }

    @PutMapping
    public void updateDoctorAppointment(@RequestBody DoctorAppointment doctorAppointment) {
        doctorAppointmentRepository.updateDoctorAppointment(doctorAppointment);
    }

    @DeleteMapping("/id")
    public void deleteDoctorAppointmentById(@PathVariable Long id) {
        doctorAppointmentRepository.deleteDoctorAppointmentById(id);
    }

    @DeleteMapping
    public void deleteAllDoctorAppointments() {
        doctorAppointmentRepository.deleteAllDoctorAppointments();
    }

    @GetMapping
    public List<DoctorAppointment> getAllDoctorAppointments() {
        return doctorAppointmentRepository.getAllDoctorAppointments();
    }
}