package com.bfu.dentalclinic.controller.payload;

import com.bfu.dentalclinic.entity.Doctor;
import com.bfu.dentalclinic.entity.Patient;
import com.bfu.dentalclinic.entity.Reason;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Long id;
    private Patient patient;
    private Doctor doctor;
    private LocalDate dateOfAppointment;
    private LocalDate timeOfVisit;
    private Reason reason;
}