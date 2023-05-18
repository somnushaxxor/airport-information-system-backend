package ru.nsu.fit.kolesnik.airportinformationsystem.specialization;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/specializations")
public class SpecializationController {

    private final SpecializationService specializationService;

    @GetMapping
    public List<SpecializationPreviewDto> getAllSpecializations() {
        return specializationService.getAllSpecializations().stream().map(SpecializationMapper::toPreviewDto).toList();
    }

    @GetMapping("/{id}")
    public SpecializationDto getSpecializationById(@PathVariable Long id) {
        return SpecializationMapper.toDto(specializationService.getSpecializationById(id));
    }

    @PostMapping
    public void createSpecialization(@Valid @RequestBody SpecializationCreationRequest creationRequest) {
        specializationService.createSpecialization(creationRequest);
    }

    @PutMapping
    public void updateSpecialization(@Valid @RequestBody SpecializationUpdateRequest updateRequest) {
        specializationService.updateSpecialization(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteSpecializationById(@PathVariable Long id) {
        specializationService.deleteSpecializationById(id);
    }

}
