package com.bfu.dentalclinic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryOfTreatment {
    private Long id;
    private Long appointmentId;
    private String description;
    private BigDecimal price;
    private BigDecimal discount;
    private LocalDate dateOfTreatment;
}
