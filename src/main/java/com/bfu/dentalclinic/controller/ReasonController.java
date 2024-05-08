package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.controller.payload.NewReasonPayload;
import com.bfu.dentalclinic.entity.Reason;
import com.bfu.dentalclinic.repository.ReasonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/hospital/reason")
public class ReasonController {

    private final ReasonRepository reasonRepository;

    @GetMapping("list")
    public String getAllReasons(Model model) {
        model.addAttribute("reasons", this.reasonRepository.findAll());
        return "reason/list";
    }

    @GetMapping("admin")
    public String getAdminReasons(Model model) {
        model.addAttribute("reasons", this.reasonRepository.findAll());
        return "reason/admin-reason";
    }

    @PostMapping("create-reason")
    public String createReason(NewReasonPayload payload) {
        reasonRepository.create(payload.title());
        return "redirect:/hospital/reason/admin";
    }

    @PutMapping("/{id}")
    public void updateReason(@PathVariable Long id, @RequestBody Reason reason) {
        reason.setId(id);
        reasonRepository.update(reason);
    }

    @PostMapping("/{id}")
    public String deleteReasonById(@PathVariable Long id) {
        reasonRepository.deleteById(id);
        return "redirect:/hospital/reason/admin";
    }

    @DeleteMapping
    public void deleteAllReasons() {
        reasonRepository.deleteAll();
    }
}