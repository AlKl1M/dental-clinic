package com.bfu.dentalclinic.controller.payload.doctor;

import jakarta.validation.constraints.NotNull;

public record NewDoctorPayload(
        @NotNull(message = "Имя не может быть null")
        String firstName,
        @NotNull(message = "Фамилия не может быть null")
        String lastName,
        @NotNull(message = "Специальность не может быть null")
        String speciality_name) {
}
