package com.bfu.dentalclinic.controller.payload;

import java.time.LocalDate;

public record NewPatientPayload(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String phoneNumber) {
}
