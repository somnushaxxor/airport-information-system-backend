package ru.nsu.fit.kolesnik.airportinformationsystem.flight.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/flights/categories")
public class FlightCategoryController {

    private final FlightCategoryService flightCategoryService;

    @GetMapping
    public List<FlightCategoryPreviewDto> getAllFlightCategories() {
        return flightCategoryService.getAllFlightCategories().stream().map(FlightCategoryMapper::toPreviewDto).toList();
    }

    @GetMapping("/{id}")
    public FlightCategoryDto getFlightCategoryById(@PathVariable Long id) {
        return FlightCategoryMapper.toDto(flightCategoryService.getFlightCategoryById(id));
    }

    @PostMapping
    public void createFlightCategory(@Valid @RequestBody FlightCategoryCreationRequest creationRequest) {
        flightCategoryService.createFlightCategory(creationRequest);
    }

    @PutMapping
    public void updateFlightCategory(@Valid @RequestBody FlightCategoryUpdateRequest updateRequest) {
        flightCategoryService.updateFlightCategory(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteFlightCategoryById(@PathVariable Long id) {
        flightCategoryService.deleteFlightCategoryById(id);
    }

}
