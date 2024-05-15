package com.bfu.dentalclinic.controller.payload;

import java.time.LocalDate;

public record UpdateAppointmentPayload(
        Long patient_id,
        Long doctor_id,
        LocalDate dateOfAppointment,
        LocalDate timeOfVisit,
        Long reason_id
) {
}
