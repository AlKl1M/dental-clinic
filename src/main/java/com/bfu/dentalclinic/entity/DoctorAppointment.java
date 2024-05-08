package com.bfu.dentalclinic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAppointment {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDate dateOfAppointment;
    private LocalDate timeOfVisit;
    private Long reasonId;
}
