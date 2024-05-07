package com.bfu.dentalclinic.repository;

import com.bfu.dentalclinic.entity.Patient;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class PatientRepository {

    private JdbcTemplate jdbcTemplate;

    public void createPatient(Patient patient) {
        String sql = "INSERT INTO dental.patient (first_name, last_name, date_of_birth, phone_number) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, patient.getFirstName(), patient.getLastName(), patient.getDateOfBirth(), patient.getPhoneNumber());
    }

    public void updatePatient(Patient patient) {
        String sql = "UPDATE dental.patient SET first_name = ?, last_name = ?, date_of_birth = ?, phone_number = ? WHERE id = ?";
        jdbcTemplate.update(sql, patient.getFirstName(), patient.getLastName(), patient.getDateOfBirth(), patient.getPhoneNumber(), patient.getId());
    }

    public void deletePatientById(Long id) {
        String sql = "DELETE FROM dental.patient WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void deleteAllPatients() {
        String sql = "DELETE FROM dental.patient";
        jdbcTemplate.update(sql);
    }

    public List<Patient> getAllPatients() {
        String sql = "SELECT * FROM dental.patient";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Patient(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getObject("date_of_birth", LocalDate.class),
                rs.getString("phone_number")
        ));
    }
}