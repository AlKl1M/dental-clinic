package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.entity.Speciality;
import com.bfu.dentalclinic.repository.SpecialityRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/speciality")
@AllArgsConstructor
public class SpecialityController {
    private final SpecialityRepository specialityRepository;

    @GetMapping
    public List<Speciality> getAllSpecialities() {
        return specialityRepository.getAllSpecialities();
    }

    @PostMapping
    public Long createSpeciality(@RequestBody Speciality speciality) {
        return specialityRepository.createSpeciality(speciality);
    }

    @PutMapping("/id")
    public void updateSpecialityName(@PathVariable Long id, @RequestParam String newName) {
        specialityRepository.updateSpecialityName(id, newName);
    }

    @DeleteMapping("/id")
    public void deleteSpeciality(@PathVariable Long id) {
        specialityRepository.deleteSpeciality(id);
    }

    @DeleteMapping
    public void deleteAllSpecialities() {
        specialityRepository.deleteAllSpecialities();
    }
}
