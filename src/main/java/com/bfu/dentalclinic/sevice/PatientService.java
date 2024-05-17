package com.bfu.dentalclinic.sevice;

import com.bfu.dentalclinic.entity.Patient;
import com.bfu.dentalclinic.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.getAllPatients();
    }

    public List<Patient> searchPatients(String search) {
        return patientRepository.searchPatients(search);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.getPatientById(id);
    }

    public void createPatient(String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber) {
        patientRepository.createPatient(firstName, lastName, dateOfBirth, phoneNumber);
    }

    public void updatePatient(Long id, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber) {
        patientRepository.updatePatient(id, firstName, lastName, dateOfBirth, phoneNumber);
    }

    public void deletePatientById(Long id) {
        patientRepository.deletePatientById(id);
    }

    public void deleteAllPatients() {
        patientRepository.deleteAllPatients();
    }
}
