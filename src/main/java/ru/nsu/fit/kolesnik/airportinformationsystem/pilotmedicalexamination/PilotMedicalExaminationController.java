package ru.nsu.fit.kolesnik.airportinformationsystem.pilotmedicalexamination;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pilots-medical-examinations")
public class PilotMedicalExaminationController {

    private final PilotMedicalExaminationService pilotMedicalExaminationService;

    @GetMapping
    public List<PilotMedicalExaminationPreviewDto> getAllPilotsMedicalExaminationsBy() {
        return pilotMedicalExaminationService.getAllPilotsMedicalExaminationsBy().stream().map(PilotMedicalExaminationMapper::toPreviewDto).toList();
    }

    @GetMapping("/{id}")
    public PilotMedicalExaminationDto getPilotMedicalExaminationById(@PathVariable Long id) {
        return PilotMedicalExaminationMapper.toDto(pilotMedicalExaminationService.getPilotMedicalExaminationById(id));
    }

    @PostMapping
    public void createRoute(@Valid @RequestBody PilotMedicalExaminationCreationRequest creationRequest) {
        pilotMedicalExaminationService.createPilotMedicalExamination(creationRequest);
    }

    @PutMapping
    public void updateRoute(@Valid @RequestBody PilotMedicalExaminationUpdateRequest updateRequest) {
        pilotMedicalExaminationService.updatePilotMedicalExamination(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteRouteById(@PathVariable Long id) {
        pilotMedicalExaminationService.deletePilotMedicalExaminationById(id);
    }

}
