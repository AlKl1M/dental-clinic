package com.bfu.dentalclinic.sevice;

import com.bfu.dentalclinic.entity.Speciality;
import com.bfu.dentalclinic.repository.SpecialityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SpecialityService {
    private final SpecialityRepository specialityRepository;

    public List<Speciality> getAllSpecialities() {
        return specialityRepository.getAllSpecialities();
    }

    public void createSpeciality(String name) {
        specialityRepository.createSpeciality(name);
    }

    public void updateSpecialityName(Long id, String name) {
        specialityRepository.updateSpecialityName(id, name);
    }

    public void deleteSpecialityById(Long id) {
        specialityRepository.deleteSpecialityById(id);
    }

    public void deleteAllSpecialities() {
        specialityRepository.deleteAllSpecialities();
    }
}
