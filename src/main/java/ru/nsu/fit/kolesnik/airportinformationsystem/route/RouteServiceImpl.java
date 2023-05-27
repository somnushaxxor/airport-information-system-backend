package ru.nsu.fit.kolesnik.airportinformationsystem.route;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;
import ru.nsu.fit.kolesnik.airportinformationsystem.airport.Airport;
import ru.nsu.fit.kolesnik.airportinformationsystem.airport.AirportService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final AirportService airportService;

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
        Airport departureAirport = airportService.getAirportById(creationRequest.departureAirportId());
        Airport transeferAirport = null;
        if (creationRequest.transferAirportId() != null) {
            transeferAirport = airportService.getAirportById(creationRequest.transferAirportId());
        }
        Airport arrivalAirport = airportService.getAirportById(creationRequest.arrivalAirportId());
        if (isRouteIncorrect(departureAirport, transeferAirport, arrivalAirport)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Route airports must be unique");
        }
        if (routeRepository.existsByDepartureAirportAndTransferAirportAndArrivalAirport(departureAirport,
                transeferAirport, arrivalAirport)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Such route already exists");
        }
        Route route = new Route();
        route.setDepartureAirport(departureAirport);
        route.setTransferAirport(transeferAirport);
        route.setArrivalAirport(arrivalAirport);
        routeRepository.save(route);
    }

    private boolean isRouteIncorrect(Airport departureAirport, Airport transferAirport, Airport arrivalAirport) {
        return departureAirport.equals(transferAirport) || departureAirport.equals(arrivalAirport)
                || arrivalAirport.equals(transferAirport);
    }

    @Override
    public void updateRoute(RouteUpdateRequest updateRequest) {
        Route route = getRouteById(updateRequest.id());
        Airport departureAirport = airportService.getAirportById(updateRequest.departureAirportId());
        Airport transeferAirport = null;
        if (updateRequest.transferAirportId() != null) {
            transeferAirport = airportService.getAirportById(updateRequest.transferAirportId());
        }
        Airport arrivalAirport = airportService.getAirportById(updateRequest.arrivalAirportId());
        if (isRouteIncorrect(departureAirport, transeferAirport, arrivalAirport)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Route airports must be unique");
        }
        if (routeRepository.existsByDepartureAirportAndTransferAirportAndArrivalAirport(departureAirport,
                transeferAirport, arrivalAirport)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Such route already exists");
        }
        route.setDepartureAirport(departureAirport);
        route.setTransferAirport(transeferAirport);
        route.setArrivalAirport(arrivalAirport);
        routeRepository.save(route);
    }

    @Override
    public void deleteRouteById(Long id) {
        Route route = getRouteById(id);
        routeRepository.delete(route);
    }

}
