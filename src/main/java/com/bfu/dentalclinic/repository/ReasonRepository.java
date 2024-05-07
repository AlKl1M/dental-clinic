package com.bfu.dentalclinic.repository;

import com.bfu.dentalclinic.entity.Reason;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ReasonRepository {
    private final JdbcTemplate jdbcTemplate;

    public void create(Reason reason) {
        String sql = "INSERT INTO dental.reason(title) VALUES (?)";
        jdbcTemplate.update(sql, reason.getTitle());
    }

    public void update(Reason reason) {
        String sql = "UPDATE dental.reason SET title = ? WHERE id = ?";
        jdbcTemplate.update(sql, reason.getTitle(), reason.getId());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM dental.reason WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void deleteAll() {
        String sql = "DELETE FROM dental.reason";
        jdbcTemplate.update(sql);
    }

    public List<Reason> findAll() {
        String sql = "SELECT * FROM dental.reason";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Reason(rs.getLong("id"), rs.getString("title")));
    }
}
