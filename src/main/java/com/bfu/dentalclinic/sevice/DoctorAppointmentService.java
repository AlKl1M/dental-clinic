package com.bfu.dentalclinic.sevice;

import com.bfu.dentalclinic.controller.payload.appointment.AppointmentDTO;
import com.bfu.dentalclinic.controller.payload.appointment.NewAppointmentPayload;
import com.bfu.dentalclinic.repository.DoctorAppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class DoctorAppointmentService {
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    public List<AppointmentDTO> getAllDoctorAppointments() {
        return doctorAppointmentRepository.getAllDoctorAppointments();
    }

    public AppointmentDTO getDoctorAppointmentById(Long appointmentId) {
        return doctorAppointmentRepository.getDoctorAppointmentById(appointmentId);
    }

    public void createDoctorAppointment(NewAppointmentPayload doctorAppointment) {
        doctorAppointmentRepository.createDoctorAppointment(doctorAppointment);
    }

    public void updateDoctorAppointment(Long id, Long patient_id, Long doctor_id, LocalDate dateOfAppointment, LocalDate timeOfVisit, Long reason_id) {
        doctorAppointmentRepository.updateDoctorAppointment(id, patient_id, doctor_id, dateOfAppointment, timeOfVisit, reason_id);
    }

    public void deleteDoctorAppointmentById(Long id) {
        doctorAppointmentRepository.deleteDoctorAppointmentById(id);
    }

    public void deleteAllDoctorAppointments() {
        doctorAppointmentRepository.deleteAllDoctorAppointments();
    }
}
