package ru.nsu.fit.kolesnik.airportinformationsystem.airplane;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/airplanes")
public class AirplaneController {

    private final AirplaneService airplaneService;

    @GetMapping
    public List<AirplanePreviewDto> getAllAirplanesBy(
            @RequestParam(value = "homeAirportId", required = false) Long homeAirportId,
            @RequestParam(value = "joinedAt", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate joinedAt,
            @Min(0) @RequestParam(value = "flightsNumber", required = false) Integer flightsNumber,
            @Min(0) @RequestParam(value = "ageInYears", required = false) Integer ageInYears
    ) {
        return airplaneService.getAllAirplanesBy(homeAirportId, joinedAt, flightsNumber, ageInYears).stream()
                .map(AirplaneMapper::toPreviewDto).toList();
    }

    @GetMapping("/{id}")
    public AirplaneDto getAirplaneById(@PathVariable Long id) {
        return AirplaneMapper.toDto(airplaneService.getAirplaneById(id));
    }

    @PostMapping
    public void createAirplane(@Valid @RequestBody AirplaneCreationRequest creationRequest) {
        airplaneService.createAirplane(creationRequest);
    }

    @PutMapping
    public void updateAirplane(@Valid @RequestBody AirplaneUpdateRequest updateRequest) {
        airplaneService.updateAirplane(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteAirplaneById(@PathVariable Long id) {
        airplaneService.deleteAirplaneById(id);
    }

}
