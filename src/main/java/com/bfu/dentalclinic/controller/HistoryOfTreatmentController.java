package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.controller.payload.NewHistoryPayload;
import com.bfu.dentalclinic.entity.HistoryOfTreatment;
import com.bfu.dentalclinic.repository.HistoryOfTreatmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("hospital/history-of-treatment")
public class HistoryOfTreatmentController {

    private final HistoryOfTreatmentRepository historyOfTreatmentRepository;

    @GetMapping("list")
    public String getAllHistories(Model model) {
        model.addAttribute("histories", historyOfTreatmentRepository.findAll());
        return "treatment/list";
    }

    @GetMapping("admin")
    public String getAllHistoriesForAdmin(Model model) {
        model.addAttribute("histories", historyOfTreatmentRepository.findAll());
        return "treatment/admin-treatment";
    }

    @PostMapping("create-history")
    public String createHistoryOfTreatment(NewHistoryPayload historyOfTreatment) {
        historyOfTreatmentRepository.create(historyOfTreatment);
        return "redirect:/hospital/history-of-treatment/admin";
    }

    @PutMapping
    public void updateHistoryOfTreatment(@RequestBody HistoryOfTreatment historyOfTreatment) {
        historyOfTreatmentRepository.update(historyOfTreatment);
    }

    @PostMapping("/{id}")
    public String deleteHistoryOfTreatment(@PathVariable Long id) {
        historyOfTreatmentRepository.deleteById(id);
        return "redirect:/hospital/history-of-treatment/admin";
    }

    @DeleteMapping
    public void deleteAllHistoryOfTreatments() {
        historyOfTreatmentRepository.deleteAll();
    }
}