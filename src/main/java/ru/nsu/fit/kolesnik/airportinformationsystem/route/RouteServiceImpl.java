package ru.nsu.fit.kolesnik.airportinformationsystem.route;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Route not found: " + id));
    }

    @Override
    public void createRoute(RouteCreationRequest creationRequest) {
        //TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateRoute(RouteUpdateRequest updateRequest) {
        //TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteRouteById(Long id) {
        //TODO
        throw new UnsupportedOperationException();
    }

}
