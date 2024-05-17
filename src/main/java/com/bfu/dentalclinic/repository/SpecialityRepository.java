package com.bfu.dentalclinic.repository;

import com.bfu.dentalclinic.entity.Speciality;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class SpecialityRepository {
    private final JdbcTemplate jdbcTemplate;
    public List<Speciality> getAllSpecialities() {
        String sql = "SELECT * FROM speciality";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Speciality(rs.getLong("id"), rs.getString("name")));
    }

    public Long findSpecialityIdByName(String specialityName) {
        return jdbcTemplate.queryForObject("SELECT id FROM speciality WHERE name = ?", Long.class, specialityName);
    }

    public void createSpeciality(String name) {
        jdbcTemplate.update("INSERT INTO speciality (name) VALUES (?)", name);
    }

    public void updateSpecialityName(Long id, String name) {
        String sql = "UPDATE speciality SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, name, id);
    }

    public void deleteSpecialityById(Long id) {
        String sql = "DELETE FROM speciality WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void deleteAllSpecialities() {
        String sql = "DELETE FROM speciality";
        jdbcTemplate.update(sql);
    }
}
