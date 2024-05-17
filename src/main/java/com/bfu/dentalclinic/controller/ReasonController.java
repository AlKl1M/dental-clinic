package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.controller.payload.reason.NewReasonPayload;
import com.bfu.dentalclinic.entity.Reason;
import com.bfu.dentalclinic.repository.ReasonRepository;
import com.bfu.dentalclinic.sevice.ReasonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/hospital/reason")
public class ReasonController {

    private final ReasonService reasonService;

    @GetMapping("list")
    public String getAllReasons(Model model) {
        model.addAttribute("reasons", this.reasonService.getAllReasons());
        return "reason/list";
    }

    @PostMapping("create-reason")
    public String createReason(@Valid NewReasonPayload payload) {
        reasonService.createReason(payload.title());
        return "redirect:/hospital/reason/list";
    }

    @PutMapping("/{id}")
    public void updateReason(@PathVariable Long id, @RequestBody Reason reason) {
        reason.setId(id);
        reasonService.updateReason(reason.getId(), reason.getTitle());
    }

    @PostMapping("/{id}")
    public String deleteReasonById(@PathVariable Long id) {
        reasonService.deleteReasonById(id);
        return "redirect:/hospital/reason/list";
    }

    @PostMapping("/delete-reasons")
    public String deleteAllReasons() {
        reasonService.deleteAllReasons();
        return "redirect:/hospital/reason/list";
    }
}