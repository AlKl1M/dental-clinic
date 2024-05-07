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

    public Long createSpeciality(Speciality speciality) {
        String sql = "INSERT INTO speciality (name) VALUES (?) RETURNING id";
        return jdbcTemplate.queryForObject(sql, Long.class, speciality.getName());
    }

    public void updateSpecialityName(Long id, String newName) {
        String sql = "UPDATE speciality SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, newName, id);
    }

    public void deleteSpeciality(Long id) {
        String sql = "DELETE FROM speciality WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void deleteAllSpecialities() {
        String sql = "DELETE FROM speciality";
        jdbcTemplate.update(sql);
    }
}
