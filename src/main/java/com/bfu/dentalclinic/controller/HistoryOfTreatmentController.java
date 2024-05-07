package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.entity.HistoryOfTreatment;
import com.bfu.dentalclinic.repository.HistoryOfTreatmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/history-of-treatment")
public class HistoryOfTreatmentController {

    private final HistoryOfTreatmentRepository historyOfTreatmentRepository;

    @PostMapping
    public void createHistoryOfTreatment(@RequestBody HistoryOfTreatment historyOfTreatment) {
        historyOfTreatmentRepository.create(historyOfTreatment);
    }

    @PutMapping
    public void updateHistoryOfTreatment(@RequestBody HistoryOfTreatment historyOfTreatment) {
        historyOfTreatmentRepository.update(historyOfTreatment);
    }

    @DeleteMapping("/{id}")
    public void deleteHistoryOfTreatment(@PathVariable Long id) {
        historyOfTreatmentRepository.deleteById(id);
    }

    @GetMapping
    public List<HistoryOfTreatment> getAllHistoryOfTreatments() {
        return historyOfTreatmentRepository.findAll();
    }

    @DeleteMapping
    public void deleteAllHistoryOfTreatments() {
        historyOfTreatmentRepository.deleteAll();
    }
}