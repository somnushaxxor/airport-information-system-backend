package ru.nsu.fit.kolesnik.airportinformationsystem.flight;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public List<FlightPreviewDto> getAllFlightsBy(
            @RequestParam(name = "airplaneId", required = false) Long airplaneId,
            @RequestParam(name = "airplaneModelId", required = false) Long airplaneModelId,
            @RequestParam(name = "routeId", required = false) Long routeId,
            @RequestParam(name = "categoryId", required = false) Long categoryId,
            @Min(0) @RequestParam(name = "ticketPrice", required = false) Integer ticketPrice
    ) {
        return flightService.getAllFlightsBy(airplaneId, airplaneModelId, routeId, categoryId, ticketPrice).stream()
                .map(FlightMapper::toPreviewDto).toList();
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
