package com.bfu.dentalclinic.controller.payload.reason;

import jakarta.validation.constraints.NotNull;

public record NewReasonPayload(
        @NotNull(message = "Название причины не может быть null")
        String title) {
}
