package com.bfu.dentalclinic.repository;

import com.bfu.dentalclinic.controller.payload.DoctorDTO;
import com.bfu.dentalclinic.entity.Doctor;
import com.bfu.dentalclinic.entity.Patient;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class DoctorRepository {

    private final JdbcTemplate jdbcTemplate;

    public void createDoctor(Doctor doctor) {
        jdbcTemplate.update("INSERT INTO doctor (first_name, last_name, speciality_id) VALUES (?, ?, ?)",
                doctor.getFirstName(), doctor.getLastName(), doctor.getSpeciality_id());
    }

    public void updateDoctor(Long id,  String firstName, String lastName, Long speciality_id) {
        jdbcTemplate.update("UPDATE doctor SET first_name = ?, last_name = ?, speciality_id = ? WHERE id = ?",
                firstName, firstName, speciality_id, id);
    }

    public void deleteDoctorById(Long id) {
        jdbcTemplate.update("DELETE FROM doctor WHERE id = ?", id);
    }

    public void deleteAllDoctors() {
        jdbcTemplate.update("DELETE FROM doctor");
    }

    public List<DoctorDTO> getAllDoctors() {
        return jdbcTemplate.query("SELECT d.id, d.first_name, d.last_name, s.name as speciality_name FROM doctor d INNER JOIN speciality s ON d.speciality_id = s.id",
                (rs, rowNum) -> new DoctorDTO(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("speciality_name")));
    }

    public Doctor getDoctorById(Long id) {
        String sql = "SELECT * FROM dental.doctor WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Doctor doctor = new Doctor();
            doctor.setId(rs.getLong("id"));
            doctor.setFirstName(rs.getString("first_name"));
            doctor.setLastName(rs.getString("last_name"));
            doctor.setSpeciality_id(rs.getLong("speciality_id"));
            return doctor;
        });
    }

}
