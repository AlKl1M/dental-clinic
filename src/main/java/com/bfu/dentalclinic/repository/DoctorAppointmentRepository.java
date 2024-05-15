package com.bfu.dentalclinic.repository;

import com.bfu.dentalclinic.controller.payload.AppointmentDTO;
import com.bfu.dentalclinic.controller.payload.NewAppointmentPayload;
import com.bfu.dentalclinic.entity.Doctor;
import com.bfu.dentalclinic.entity.DoctorAppointment;
import com.bfu.dentalclinic.entity.Patient;
import com.bfu.dentalclinic.entity.Reason;
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

    public void createDoctorAppointment(NewAppointmentPayload doctorAppointment) {
        String sql = "INSERT INTO doctor_appointment (id_patient, id_doctor, date_of_appointment, time_of_visit, reason_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, doctorAppointment.patient_id(), doctorAppointment.doctor_id(),
                doctorAppointment.dateOfAppointment(), doctorAppointment.timeOfVisit(),
                doctorAppointment.reason_id());
    }

    public void updateDoctorAppointment(Long id, Long patient_id, Long doctor_id, LocalDate dateOfAppointment, LocalDate timeOfVisit, Long reason_id) {
        String sql = "UPDATE doctor_appointment SET id_patient = ?, id_doctor = ?, date_of_appointment = ?, time_of_visit = ?, reason_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, patient_id, doctor_id,
                dateOfAppointment, timeOfVisit,
                reason_id, id);
    }

    public void deleteDoctorAppointmentById(Long id) {
        String sql = "DELETE FROM doctor_appointment WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void deleteAllDoctorAppointments() {
        String sql = "DELETE FROM doctor_appointment";
        jdbcTemplate.update(sql);
    }

    public List<AppointmentDTO> getAllDoctorAppointments() {
        String sql = "SELECT da.id, da.id_patient, da.id_doctor, da.date_of_appointment, da.time_of_visit, da.reason_id, "
                + "p.id as patient_id, p.first_name as patient_firstName, p.last_name as patient_lastName, p.date_of_birth as patient_dateOfBirth, p.phone_number as patient_phoneNumber, "
                + "d.id as doctor_id, d.first_name as doctor_firstName, d.last_name as doctor_lastName, d.speciality_id as doctor_speciality_id, "
                + "r.id as reason_id, r.title as reason_title "
                + "FROM doctor_appointment da "
                + "INNER JOIN patient p ON da.id_patient = p.id "
                + "INNER JOIN doctor d ON da.id_doctor = d.id "
                + "INNER JOIN reason r ON da.reason_id = r.id";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new AppointmentDTO(
                rs.getLong("id"),
                new Patient(
                        rs.getLong("patient_id"),
                        rs.getString("patient_firstName"),
                        rs.getString("patient_lastName"),
                        rs.getObject("patient_dateOfBirth", LocalDate.class),
                        rs.getString("patient_phoneNumber")
                ),
                new Doctor(
                        rs.getLong("doctor_id"),
                        rs.getString("doctor_firstName"),
                        rs.getString("doctor_lastName"),
                        rs.getLong("doctor_speciality_id")
                ),
                rs.getObject("date_of_appointment", LocalDate.class),
                rs.getObject("time_of_visit", LocalDate.class),
                new Reason(
                        rs.getLong("reason_id"),
                        rs.getString("reason_title")
                )
        ));
    }

    public AppointmentDTO getDoctorAppointmentById(Long appointmentId) {
        String sql = "SELECT da.id, da.id_patient, da.id_doctor, da.date_of_appointment, da.time_of_visit, da.reason_id, "
                + "p.id as patient_id, p.first_name as patient_firstName, p.last_name as patient_lastName, p.date_of_birth as patient_dateOfBirth, p.phone_number as patient_phoneNumber, "
                + "d.id as doctor_id, d.first_name as doctor_firstName, d.last_name as doctor_lastName, d.speciality_id as doctor_speciality_id, "
                + "r.id as reason_id, r.title as reason_title "
                + "FROM doctor_appointment da "
                + "INNER JOIN patient p ON da.id_patient = p.id "
                + "INNER JOIN doctor d ON da.id_doctor = d.id "
                + "INNER JOIN reason r ON da.reason_id = r.id "
                + "WHERE da.id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{appointmentId}, (rs, rowNum) -> new AppointmentDTO(
                rs.getLong("id"),
                new Patient(
                        rs.getLong("patient_id"),
                        rs.getString("patient_firstName"),
                        rs.getString("patient_lastName"),
                        rs.getObject("patient_dateOfBirth", LocalDate.class),
                        rs.getString("patient_phoneNumber")
                ),
                new Doctor(
                        rs.getLong("doctor_id"),
                        rs.getString("doctor_firstName"),
                        rs.getString("doctor_lastName"),
                        rs.getLong("doctor_speciality_id")
                ),
                rs.getObject("date_of_appointment", LocalDate.class),
                rs.getObject("time_of_visit", LocalDate.class),
                new Reason(
                        rs.getLong("reason_id"),
                        rs.getString("reason_title")
                )
        ));
    }
}