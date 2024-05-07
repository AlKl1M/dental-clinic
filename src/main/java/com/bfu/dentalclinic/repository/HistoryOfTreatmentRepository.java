package com.bfu.dentalclinic.repository;

import com.bfu.dentalclinic.entity.HistoryOfTreatment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoryOfTreatmentRepository {

    private final JdbcTemplate jdbcTemplate;

    public HistoryOfTreatmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(HistoryOfTreatment historyOfTreatment) {
        String sql = "INSERT INTO history_of_treatment(appointment_id, description, price, discount, date_of_treatment) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, historyOfTreatment.getAppointmentId(), historyOfTreatment.getDescription(),
                historyOfTreatment.getPrice(), historyOfTreatment.getDiscount(), historyOfTreatment.getDateOfTreatment());
    }

    public void update(HistoryOfTreatment historyOfTreatment) {
        String sql = "UPDATE history_of_treatment SET appointment_id = ?, description = ?, price = ?, discount = ?, date_of_treatment = ? WHERE id = ?";
        jdbcTemplate.update(sql, historyOfTreatment.getAppointmentId(), historyOfTreatment.getDescription(),
                historyOfTreatment.getPrice(), historyOfTreatment.getDiscount(), historyOfTreatment.getDateOfTreatment(),
                historyOfTreatment.getId());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM history_of_treatment WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void deleteAll() {
        String sql = "DELETE FROM history_of_treatment";
        jdbcTemplate.update(sql);
    }

    public List<HistoryOfTreatment> findAll() {
        String sql = "SELECT * FROM history_of_treatment";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new HistoryOfTreatment(
                rs.getLong("id"),
                rs.getLong("appointment_id"),
                rs.getString("description"),
                rs.getBigDecimal("price"),
                rs.getBigDecimal("discount"),
                rs.getDate("date_of_treatment").toLocalDate()
        ));
    }
}