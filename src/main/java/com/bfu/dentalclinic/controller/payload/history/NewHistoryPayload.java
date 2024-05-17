package com.bfu.dentalclinic.controller.payload.history;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NewHistoryPayload(
        Long id,
        Long appointmentId,
        String description,
        BigDecimal price,
        BigDecimal discount,
        LocalDate dateOfTreatment
) {
}
