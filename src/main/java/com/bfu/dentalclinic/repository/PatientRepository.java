package com.bfu.dentalclinic.repository;

import com.bfu.dentalclinic.controller.payload.UpdatePatientPayload;
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

    public void createPatient(String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber) {
        String sql = "INSERT INTO dental.patient (first_name, last_name, date_of_birth, phone_number) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, firstName, lastName, dateOfBirth, phoneNumber);
    }

    public void updatePatient(Long id, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber) {
        String sql = "UPDATE dental.patient SET first_name = ?, last_name = ?, date_of_birth = ?, phone_number = ? WHERE id = ?";
        jdbcTemplate.update(sql, firstName, lastName, dateOfBirth, phoneNumber, id);
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

    public Patient getPatientById(Long id) {
        String sql = "SELECT * FROM dental.patient WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Patient patient = new Patient();
            patient.setId(rs.getLong("id"));
            patient.setFirstName(rs.getString("first_name"));
            patient.setLastName(rs.getString("last_name"));
            patient.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
            patient.setPhoneNumber(rs.getString("phone_number"));
            return patient;
        });
    }
}