package ru.nsu.fit.kolesnik.airportinformationsystem.route;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    @GetMapping
    public List<RoutePreviewDto> getAllRoutes() {
        return routeService.getAllRoutes().stream().map(RouteMapper::toPreviewDto).toList();
    }

    @GetMapping("/{id}")
    public RouteDto getRouteById(@PathVariable Long id) {
        return RouteMapper.toDto(routeService.getRouteById(id));
    }

    @PostMapping
    public void createRoute(@Valid @RequestBody RouteCreationRequest creationRequest) {
        routeService.createRoute(creationRequest);
    }

    @PutMapping
    public void updateRoute(@Valid @RequestBody RouteUpdateRequest updateRequest) {
        routeService.updateRoute(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteRouteById(@PathVariable Long id) {
        routeService.deleteRouteById(id);
    }

}
