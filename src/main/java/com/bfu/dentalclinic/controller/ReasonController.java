package com.bfu.dentalclinic.controller;

import com.bfu.dentalclinic.entity.Reason;
import com.bfu.dentalclinic.repository.ReasonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reasons")
public class ReasonController {

    private final ReasonRepository reasonRepository;

    public ReasonController(ReasonRepository reasonRepository) {
        this.reasonRepository = reasonRepository;
    }

    @PostMapping
    public void createReason(@RequestBody Reason reason) {
        reasonRepository.create(reason);
    }

    @PutMapping("/{id}")
    public void updateReason(@PathVariable Long id, @RequestBody Reason reason) {
        reason.setId(id);
        reasonRepository.update(reason);
    }

    @DeleteMapping("/{id}")
    public void deleteReasonById(@PathVariable Long id) {
        reasonRepository.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllReasons() {
        reasonRepository.deleteAll();
    }

    @GetMapping
    public List<Reason> getAllReasons() {
        return reasonRepository.findAll();
    }
}