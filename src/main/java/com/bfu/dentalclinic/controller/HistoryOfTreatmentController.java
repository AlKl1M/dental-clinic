package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.controller.payload.history.NewHistoryPayload;
import com.bfu.dentalclinic.controller.payload.history.UpdateHistoryPayload;
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

    @GetMapping("/list/{id}")
    public String getAllHistories(@PathVariable Long id, Model model) {
        model.addAttribute("history", historyOfTreatmentRepository.findById(id));
        return "treatment/detail";
    }

    @PostMapping("create-history")
    public String createHistoryOfTreatment(NewHistoryPayload historyOfTreatment) {
        historyOfTreatmentRepository.create(historyOfTreatment);
        return "redirect:/hospital/history-of-treatment/list";
    }

    @PostMapping("/update-history/{id}")
    public String updateHistoryOfTreatment(@PathVariable Long id, UpdateHistoryPayload historyOfTreatment) {
        historyOfTreatmentRepository.update(id, historyOfTreatment.appointmentId(),
                historyOfTreatment.description(), historyOfTreatment.price(), historyOfTreatment.discount(),
                historyOfTreatment.dateOfTreatment());
        return "redirect:/hospital/history-of-treatment/list/" + id;
    }

    @PostMapping("/{id}")
    public String deleteHistoryOfTreatment(@PathVariable Long id) {
        historyOfTreatmentRepository.deleteById(id);
        return "redirect:/hospital/history-of-treatment/list";
    }

    @PostMapping("/delete-history")
    public String deleteAllHistoryOfTreatments() {
        historyOfTreatmentRepository.deleteAll();
        return "redirect:/hospital/history-of-treatment/list";
    }
}