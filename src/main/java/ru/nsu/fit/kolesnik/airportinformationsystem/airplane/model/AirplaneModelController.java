package ru.nsu.fit.kolesnik.airportinformationsystem.airplane.model;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/airplanes/models")
public class AirplaneModelController {

    private final AirplaneModelService airplaneModelService;

    @GetMapping
    public List<AirplaneModelPreviewDto> getAllAirplaneModels() {
        return airplaneModelService.getAllAirplaneModels().stream().map(AirplaneModelMapper::toPreviewDto).toList();
    }

    @GetMapping("/{id}")
    public AirplaneModelDto getAirplaneModelById(@PathVariable Long id) {
        return AirplaneModelMapper.toDto(airplaneModelService.getAirplaneModelById(id));
    }

    @PostMapping
    public void createAirplaneModel(@Valid @RequestBody AirplaneModelCreationRequest creationRequest) {
        airplaneModelService.createAirplaneModel(creationRequest);
    }

    @PutMapping
    public void updateAirplaneModel(@Valid @RequestBody AirplaneModelUpdateRequest updateRequest) {
        airplaneModelService.updateAirplaneModel(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteSpecializationById(@PathVariable Long id) {
        airplaneModelService.deleteAirplaneModelById(id);
    }

}
