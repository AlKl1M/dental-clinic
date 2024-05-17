package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.controller.payload.appointment.NewAppointmentPayload;
import com.bfu.dentalclinic.controller.payload.appointment.UpdateAppointmentPayload;
import com.bfu.dentalclinic.sevice.DoctorAppointmentService;
import com.bfu.dentalclinic.sevice.DoctorService;
import com.bfu.dentalclinic.sevice.PatientService;
import com.bfu.dentalclinic.sevice.ReasonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("hospital/appointments")
@AllArgsConstructor
public class DoctorAppointmentController {
    private final DoctorAppointmentService doctorAppointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final ReasonService reasonService;
    @GetMapping("list")
    public String getAllAppointments(Model model) {
        model.addAttribute("appointments", doctorAppointmentService.getAllDoctorAppointments());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("reasons", reasonService.getAllReasons());
        return "appointment/list";
    }

    @GetMapping("/list/{id}")
    public String getAppointmentById(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", doctorAppointmentService.getDoctorAppointmentById(id));
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("reasons", reasonService.getAllReasons());
        return "appointment/detail";
    }

    @PostMapping("create-appointment")
    public String createDoctorAppointment(NewAppointmentPayload doctorAppointment) {
        doctorAppointmentService.createDoctorAppointment(doctorAppointment);
        return "redirect:/hospital/appointments/list";
    }

    @PostMapping("update-appointment/{id}")
    public String updateDoctorAppointment(@PathVariable Long id, UpdateAppointmentPayload payload) {
        doctorAppointmentService.updateDoctorAppointment(id, payload.patient_id(), payload.doctor_id(),
                payload.dateOfAppointment(), payload.timeOfVisit(), payload.reason_id());
        return "redirect:/hospital/appointments/list/" + id;
    }

    @PostMapping("/{id}")
    public String deleteDoctorAppointmentById(@PathVariable Long id) {
        doctorAppointmentService.deleteDoctorAppointmentById(id);
        return "redirect:/hospital/appointments/list";
    }

    @PostMapping("/delete-appointments")
    public String deleteAllDoctorAppointments() {
        doctorAppointmentService.deleteAllDoctorAppointments();
        return "redirect:/hospital/appointments/list";
    }
}