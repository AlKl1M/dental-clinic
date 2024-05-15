package com.bfu.dentalclinic.controller.payload;

import java.time.LocalDate;

public record UpdatePatientPayload(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String phoneNumber
) {
}
