package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.controller.payload.NewDoctorPayload;
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
    private final DoctorService doctorService;

    @GetMapping("list")
    public String getAllDoctors(Model model) {
        model.addAttribute("doctors", this.doctorService.getAllDoctors());
        return "doctor/list";
    }

    @PostMapping("create-doctor")
    public String createDoctor(NewDoctorPayload payload,
                               Model model) {
        payload.speciality_name();
        payload.firstName();
        payload.lastName();
        doctorService.createDoctor(payload.firstName(), payload.lastName(), payload.speciality_name());
        return "redirect:/hospital/doctors/list";
    }

    @PutMapping("/{id}")
    public String updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        doctor.setId(id);
        doctorRepository.updateDoctor(doctor);
        return "redirect:/hospital/doctors/list";
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