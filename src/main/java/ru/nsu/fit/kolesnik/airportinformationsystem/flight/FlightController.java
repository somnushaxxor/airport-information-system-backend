package ru.nsu.fit.kolesnik.airportinformationsystem.flight;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public List<FlightPreviewDto> getAllFlightsBy() {
        return flightService.getAllFlightsBy().stream().map(FlightMapper::toPreviewDto).toList();
    }

    @GetMapping("/{id}")
    public FlightDto getFlightById(@PathVariable Long id) {
        return FlightMapper.toDto(flightService.getFlightById(id));
    }

    @PostMapping
    public void createFlight(@Valid @RequestBody FlightCreationRequest creationRequest) {
        flightService.createFlight(creationRequest);
    }

    @PutMapping
    public void updateFlight(@Valid @RequestBody FlightUpdateRequest updateRequest) {
        flightService.updateFlight(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteFlightById(@PathVariable Long id) {
        flightService.deleteFlightById(id);
    }

}
