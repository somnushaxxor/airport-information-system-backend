package ru.nsu.fit.kolesnik.airportinformationsystem.brigade;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/brigades")
public class BrigadeController {

    private final BrigadeService brigadeService;
    @Value("${specializations.core.pilot}")
    private String pilotSpecializationName;
    @Value("${specializations.core.technician}")
    private String technicianSpecializationName;
    @Value("${specializations.core.service}")
    private String serviceSpecializationName;

    @GetMapping
    public List<BrigadePreviewDto> getAllBrigadesBy(
            @RequestParam(value = "specializationId", required = false) Long specializationId,
            @RequestParam(value = "departmentId", required = false) Long departmentId
    ) {
        return brigadeService.getAllBrigadesBy(specializationId, departmentId).stream()
                .map(BrigadeMapper::toPreviewDto).toList();
    }

    @GetMapping("/{id}")
    public BrigadeDto getBrigadeById(@PathVariable Long id) {
        return BrigadeMapper.toDto(brigadeService.getBrigadeById(id));
    }

    @PostMapping
    public void createBrigade(@Valid @RequestBody BrigadeCreationRequest creationRequest) {
        brigadeService.createBrigade(creationRequest);
    }

    @PutMapping
    public void updateBrigade(@Valid @RequestBody BrigadeUpdateRequest updateRequest) {
        brigadeService.updateBrigade(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteBrigadeById(@PathVariable Long id) {
        brigadeService.deleteBrigadeById(id);
    }

    @GetMapping("/pilots")
    public List<BrigadePreviewDto> getAllNonemptyPilotsBrigades() {
        return brigadeService.getAllNonemptyBrigadesBySpecializationName(pilotSpecializationName).stream()
                .map(BrigadeMapper::toPreviewDto).toList();
    }

    @GetMapping("/technicians")
    public List<BrigadePreviewDto> getAllNonemptyTechniciansBrigades() {
        return brigadeService.getAllNonemptyBrigadesBySpecializationName(technicianSpecializationName).stream()
                .map(BrigadeMapper::toPreviewDto).toList();
    }

    @GetMapping("/service")
    public List<BrigadePreviewDto> getAllNonemptyServiceBrigades() {
        return brigadeService.getAllNonemptyBrigadesBySpecializationName(serviceSpecializationName).stream()
                .map(BrigadeMapper::toPreviewDto).toList();
    }

}
