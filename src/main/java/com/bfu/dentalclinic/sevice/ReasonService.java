package com.bfu.dentalclinic.sevice;

import com.bfu.dentalclinic.entity.Reason;
import com.bfu.dentalclinic.repository.ReasonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReasonService {
    private final ReasonRepository reasonRepository;

    public List<Reason> getAllReasons() {
        return reasonRepository.getAllReasons();
    }

    public void createReason(String title) {
        reasonRepository.createReason(title);
    }

    public void updateReason(Long id, String title) {
        reasonRepository.updateReason(id, title);
    }

    public void deleteReasonById(Long id) {
        reasonRepository.deleteReasonById(id);
    }

    public void deleteAllReasons() {
        reasonRepository.deleteAllReasons();
    }

}
