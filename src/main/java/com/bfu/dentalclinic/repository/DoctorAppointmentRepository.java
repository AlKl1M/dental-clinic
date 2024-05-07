package com.bfu.dentalclinic.repository;

import com.bfu.dentalclinic.entity.DoctorAppointment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DoctorAppointmentRepository {

    private final JdbcTemplate jdbcTemplate;

    public DoctorAppointmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createDoctorAppointment(DoctorAppointment doctorAppointment) {
        String sql = "INSERT INTO doctor_appointment (id_patient, id_doctor, date_of_appointment, time_of_visit, reason_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, doctorAppointment.getPatientId(), doctorAppointment.getDoctorId(),
                doctorAppointment.getDateOfAppointment(), doctorAppointment.getTimeOfVisit(),
                doctorAppointment.getReasonId());
    }

    public void updateDoctorAppointment(DoctorAppointment doctorAppointment) {
        String sql = "UPDATE doctor_appointment SET id_patient = ?, id_doctor = ?, date_of_appointment = ?, time_of_visit = ?, reason_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, doctorAppointment.getPatientId(), doctorAppointment.getDoctorId(),
                doctorAppointment.getDateOfAppointment(), doctorAppointment.getTimeOfVisit(),
                doctorAppointment.getReasonId(), doctorAppointment.getId());
    }

    public void deleteDoctorAppointmentById(Long id) {
        String sql = "DELETE FROM doctor_appointment WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void deleteAllDoctorAppointments() {
        String sql = "DELETE FROM doctor_appointment";
        jdbcTemplate.update(sql);
    }

    public List<DoctorAppointment> getAllDoctorAppointments() {
        String sql = "SELECT * FROM doctor_appointment";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new DoctorAppointment(
                rs.getLong("id"),
                rs.getLong("id_patient"),
                rs.getLong("id_doctor"),
                rs.getObject("date_of_appointment", LocalDate.class),
                rs.getBigDecimal("time_of_visit"),
                rs.getLong("reason_id")
        ));
    }
}