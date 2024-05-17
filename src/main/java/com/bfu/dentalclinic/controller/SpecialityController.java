package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.controller.payload.speciality.NewSpecialityPayload;
import com.bfu.dentalclinic.repository.SpecialityRepository;
import com.bfu.dentalclinic.sevice.SpecialityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("hospital/speciality")
@AllArgsConstructor
public class SpecialityController {
    private final SpecialityService specialityService;

    @GetMapping("list")
    public String getAllSpecialities(Model model) {
        model.addAttribute("specialities", this.specialityService.getAllSpecialities());
        return "speciality/list";
    }

    @PostMapping("create-speciality")
    public String createSpeciality(@Valid NewSpecialityPayload payload,
                                 Model model) {
        specialityService.createSpeciality(payload.name());
        return "redirect:/hospital/speciality/list";
    }

    @PutMapping("/id")
    public void updateSpecialityName(@PathVariable Long id, @RequestParam String newName) {
        specialityService.updateSpecialityName(id, newName);
    }

    @PostMapping("/{id}")
    public String deleteSpeciality(@PathVariable Long id) {
        specialityService.deleteSpecialityById(id);
        return "redirect:/hospital/speciality/list";
    }

    @PostMapping("delete-specialities")
    public String deleteAllSpecialities() {
        specialityService.deleteAllSpecialities();
        return "redirect:/hospital/speciality/list";
    }
}
