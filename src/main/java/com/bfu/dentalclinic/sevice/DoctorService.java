package com.bfu.dentalclinic.sevice;

import com.bfu.dentalclinic.controller.payload.DoctorDTO;
import com.bfu.dentalclinic.entity.Doctor;
import com.bfu.dentalclinic.repository.DoctorRepository;
import com.bfu.dentalclinic.repository.SpecialityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final SpecialityRepository specialityRepository;

    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.getAllDoctors();
    }

    public void createDoctor(String firstName, String lastName, String specialityName) {
        Long specialityId = specialityRepository.findSpecialityIdByName(specialityName);
        Doctor doctor = new Doctor(null, firstName, lastName, specialityId);
        doctorRepository.createDoctor(doctor);
    }
}
