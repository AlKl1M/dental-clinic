package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.controller.payload.AppointmentDTO;
import com.bfu.dentalclinic.controller.payload.NewAppointmentPayload;
import com.bfu.dentalclinic.controller.payload.UpdateAppointmentPayload;
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

    @GetMapping("/list/{id}")
    public String getAppointmentById(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", doctorAppointmentRepository.getDoctorAppointmentById(id));
        model.addAttribute("doctors", doctorRepository.getAllDoctors());
        model.addAttribute("patients", patientRepository.getAllPatients());
        model.addAttribute("reasons", reasonRepository.findAll());
        return "appointment/detail";
    }

    @PostMapping("create-appointment")
    public String createDoctorAppointment(NewAppointmentPayload doctorAppointment) {
        doctorAppointmentRepository.createDoctorAppointment(doctorAppointment);
        return "redirect:/hospital/appointments/list";
    }

    @PostMapping("update-appointment/{id}")
    public String updateDoctorAppointment(@PathVariable Long id, UpdateAppointmentPayload payload) {
        doctorAppointmentRepository.updateDoctorAppointment(id, payload.patient_id(), payload.doctor_id(),
                payload.dateOfAppointment(), payload.timeOfVisit(), payload.reason_id());
        return "redirect:/hospital/appointments/list/" + id;
    }

    @PostMapping("/{id}")
    public String deleteDoctorAppointmentById(@PathVariable Long id) {
        doctorAppointmentRepository.deleteDoctorAppointmentById(id);
        return "redirect:/hospital/appointments/list";
    }

    @PostMapping("/delete-appointments")
    public String deleteAllDoctorAppointments() {
        doctorAppointmentRepository.deleteAllDoctorAppointments();
        return "redirect:/hospital/appointments/list";
    }
}