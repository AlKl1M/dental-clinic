package com.bfu.dentalclinic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    private Long id;
    private String firstName;
    private String lastName;
    private Long speciality_id;
}
