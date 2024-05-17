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

    public List<Reason> getAllReasons() {
        String sql = "SELECT * FROM dental.reason";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Reason(rs.getLong("id"), rs.getString("title")));
    }

    public void createReason(String title) {
        String sql = "INSERT INTO dental.reason(title) VALUES (?)";
        jdbcTemplate.update(sql, title);
    }

    public void updateReason(Long id, String title) {
        String sql = "UPDATE dental.reason SET title = ? WHERE id = ?";
        jdbcTemplate.update(sql, title, id);
    }

    public void deleteReasonById(Long id) {
        String sql = "DELETE FROM dental.reason WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void deleteAllReasons() {
        String sql = "DELETE FROM dental.reason";
        jdbcTemplate.update(sql);
    }
}
