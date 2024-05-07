package com.bfu.dentalclinic.repository;

import com.bfu.dentalclinic.entity.Doctor;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class DoctorRepository {

    private final JdbcTemplate jdbcTemplate;

    public void createDoctor(Doctor doctor) {
        jdbcTemplate.update("INSERT INTO doctor (id, first_name, last_name, speciality_id) VALUES (?, ?, ?, ?)",
                doctor.getId(), doctor.getFirstName(), doctor.getLastName(), doctor.getSpeciality_id());
    }

    public void updateDoctor(Doctor doctor) {
        jdbcTemplate.update("UPDATE doctor SET first_name = ?, last_name = ?, speciality_id = ? WHERE id = ?",
                doctor.getFirstName(), doctor.getLastName(), doctor.getSpeciality_id(), doctor.getId());
    }

    public void deleteDoctorById(Long id) {
        jdbcTemplate.update("DELETE FROM doctor WHERE id = ?", id);
    }

    public void deleteAllDoctors() {
        jdbcTemplate.update("DELETE FROM doctor");
    }

    public List<Doctor> getAllDoctors() {
        return jdbcTemplate.query("SELECT * FROM doctor",
                (rs, rowNum) -> new Doctor(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getLong("speciality_id")));
    }
}
