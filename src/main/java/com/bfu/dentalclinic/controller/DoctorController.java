package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.controller.payload.NewDoctorPayload;
import com.bfu.dentalclinic.controller.payload.UpdateDoctorPayload;
import com.bfu.dentalclinic.entity.Doctor;
import com.bfu.dentalclinic.repository.DoctorRepository;
import com.bfu.dentalclinic.repository.SpecialityRepository;
import com.bfu.dentalclinic.sevice.DoctorService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("hospital/doctors")
@AllArgsConstructor
public class DoctorController {
    private final DoctorRepository doctorRepository;
    private final SpecialityRepository specialityRepository;
    private final DoctorService doctorService;

    @GetMapping("list")
    public String getAllDoctors(Model model) {
        model.addAttribute("doctors", this.doctorService.getAllDoctors());
        model.addAttribute("specialities", this.specialityRepository.getAllSpecialities());
        return "doctor/list";
    }

    @GetMapping("list/{id}")
    public String getAllDoctors(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", this.doctorRepository.getDoctorById(id));
        model.addAttribute("specialities", this.specialityRepository.getAllSpecialities());
        return "doctor/detail";
    }

    @PostMapping("create-doctor")
    public String createDoctor(NewDoctorPayload payload,
                               Model model) {
        doctorService.createDoctor(payload.firstName(), payload.lastName(), payload.speciality_name());
        return "redirect:/hospital/doctors/list";
    }

    @PostMapping("/update-doctor/{id}")
    public String updateDoctor(@PathVariable Long id, UpdateDoctorPayload payload) {
        doctorRepository.updateDoctor(id, payload.firstName(), payload.lastName(), payload.speciality_id());
        return "redirect:/hospital/doctors/list/" + id;
    }

    @PostMapping("/{id}")
    public String deleteDoctorById(@PathVariable Long id) {
        doctorRepository.deleteDoctorById(id);
        return "redirect:/hospital/doctors/list";
    }

    @PostMapping("/delete-doctors")
    public String deleteAllDoctors() {
        doctorRepository.deleteAllDoctors();
        return "redirect:/hospital/doctors/list";
    }
}