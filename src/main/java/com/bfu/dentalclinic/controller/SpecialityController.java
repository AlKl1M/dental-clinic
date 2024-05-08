package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.controller.payload.NewDoctorPayload;
import com.bfu.dentalclinic.controller.payload.NewSpecialityPayload;
import com.bfu.dentalclinic.entity.Speciality;
import com.bfu.dentalclinic.repository.SpecialityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("hospital/speciality")
@AllArgsConstructor
public class SpecialityController {
    private final SpecialityRepository specialityRepository;

    @GetMapping("list")
    public String getAllSpecialities(Model model) {
        model.addAttribute("specialities", this.specialityRepository.getAllSpecialities());
        return "speciality/list";
    }

    @GetMapping("admin")
    public String getAdminSpecialities(Model model) {
        model.addAttribute("specialities", this.specialityRepository.getAllSpecialities());
        return "speciality/admin-speciality";
    }

    @PostMapping("create-speciality")
    public String createSpeciality(NewSpecialityPayload payload,
                                 Model model) {
        specialityRepository.createSpeciality(payload.name());
        return "redirect:/hospital/speciality/admin";
    }

    @PutMapping("/id")
    public void updateSpecialityName(@PathVariable Long id, @RequestParam String newName) {
        specialityRepository.updateSpecialityName(id, newName);
    }

    @PostMapping("/{id}")
    public String deleteSpeciality(@PathVariable Long id) {
        specialityRepository.deleteSpeciality(id);
        return "redirect:/hospital/speciality/admin";
    }

    @DeleteMapping
    public void deleteAllSpecialities() {
        specialityRepository.deleteAllSpecialities();
    }
}
