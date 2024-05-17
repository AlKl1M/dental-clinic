package com.bfu.dentalclinic.controller.payload.speciality;

import jakarta.validation.constraints.NotNull;

public record NewSpecialityPayload(
        @NotNull(message = "Название специальности должно быть не пустым")
        String name
) {
}
