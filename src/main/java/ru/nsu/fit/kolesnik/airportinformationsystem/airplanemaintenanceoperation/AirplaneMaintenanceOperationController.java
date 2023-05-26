package ru.nsu.fit.kolesnik.airportinformationsystem.airplanemaintenanceoperation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/airplane-maintenance-operations")
public class AirplaneMaintenanceOperationController {

    private final AirplaneMaintenanceOperationService airplaneMaintenanceOperationService;

    @GetMapping
    public List<AirplaneMaintenanceOperationPreviewDto> getAllPilotsMedicalExaminationsBy() {
        return airplaneMaintenanceOperationService.getAllAirplaneMaintenanceOperationsBy().stream().map(AirplaneMaintenanceOperationMapper::toPreviewDto).toList();
    }

    @GetMapping("/{id}")
    public AirplaneMaintenanceOperationDto getPilotMedicalExaminationById(@PathVariable Long id) {
        return AirplaneMaintenanceOperationMapper.toDto(airplaneMaintenanceOperationService.getAirplaneMaintenanceOperationById(id));
    }

    @PostMapping
    public void createPilotsMedicalExamination(@Valid @RequestBody AirplaneMaintenanceOperationCreationRequest creationRequest) {
        airplaneMaintenanceOperationService.createAirplaneMaintenanceOperation(creationRequest);
    }

    @PutMapping
    public void updatePilotsMedicalExamination(@Valid @RequestBody AirplaneMaintenanceOperationUpdateRequest updateRequest) {
        airplaneMaintenanceOperationService.updateAirplaneMaintenanceOperation(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void deletePilotsMedicalExaminationById(@PathVariable Long id) {
        airplaneMaintenanceOperationService.deleteAirplaneMaintenanceOperationById(id);
    }

}
