package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.controller.payload.doctor.DoctorDTO;
import com.bfu.dentalclinic.controller.payload.doctor.NewDoctorPayload;
import com.bfu.dentalclinic.controller.payload.doctor.UpdateDoctorPayload;
import com.bfu.dentalclinic.sevice.DoctorService;
import com.bfu.dentalclinic.sevice.SpecialityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("hospital/doctors")
@AllArgsConstructor
public class DoctorController {
    private final SpecialityService specialityService;
    private final DoctorService doctorService;

    @GetMapping("list")
    public String getAllDoctors(@RequestParam(required = false) String search, Model model) {
        List<DoctorDTO> doctors;
        if (search != null && !search.isEmpty()) {
            doctors = doctorService.getDoctorsBySearch(search);
        } else {
            doctors = doctorService.getAllDoctors();
        }
        model.addAttribute("doctors", doctors);
        model.addAttribute("specialities", specialityService.getAllSpecialities());
        return "doctor/list";
    }

    @GetMapping("list/{id}")
    public String getAllDoctors(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", this.doctorService.getDoctorById(id));
        model.addAttribute("specialities", this.specialityService.getAllSpecialities());
        return "doctor/detail";
    }

    @PostMapping("create-doctor")
    public String createDoctor(@Valid NewDoctorPayload payload,
                               Model model) {
        doctorService.createDoctor(payload.firstName(), payload.lastName(), payload.speciality_name());
        return "redirect:/hospital/doctors/list";
    }

    @PostMapping("/update-doctor/{id}")
    public String updateDoctor(@PathVariable Long id,
                               @Valid UpdateDoctorPayload payload) {
        doctorService.updateDoctor(id, payload.firstName(), payload.lastName(), payload.speciality_name());
        return "redirect:/hospital/doctors/list/" + id;
    }

    @PostMapping("/{id}")
    public String deleteDoctorById(@PathVariable Long id) {
        doctorService.deleteDoctorById(id);
        return "redirect:/hospital/doctors/list";
    }

    @PostMapping("/delete-doctors")
    public String deleteAllDoctors() {
        doctorService.deleteAllDoctors();
        return "redirect:/hospital/doctors/list";
    }
}