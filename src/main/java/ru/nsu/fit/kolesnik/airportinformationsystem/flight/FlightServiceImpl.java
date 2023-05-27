package ru.nsu.fit.kolesnik.airportinformationsystem.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;
import ru.nsu.fit.kolesnik.airportinformationsystem.airplane.Airplane;
import ru.nsu.fit.kolesnik.airportinformationsystem.airplane.AirplaneService;
import ru.nsu.fit.kolesnik.airportinformationsystem.airplane.model.AirplaneModel;
import ru.nsu.fit.kolesnik.airportinformationsystem.airplane.model.AirplaneModelService;
import ru.nsu.fit.kolesnik.airportinformationsystem.flight.category.FlightCategory;
import ru.nsu.fit.kolesnik.airportinformationsystem.flight.category.FlightCategoryService;
import ru.nsu.fit.kolesnik.airportinformationsystem.route.Route;
import ru.nsu.fit.kolesnik.airportinformationsystem.route.RouteService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirplaneService airplaneService;
    private final RouteService routeService;
    private final FlightCategoryService flightCategoryService;
    private final AirplaneModelService airplaneModelService;

    @Override
    public List<Flight> getAllFlightsBy(Long airplaneId, Long airplaneModelId, Long routeId, Long categoryId,
                                        Integer ticketPrice) {
        Airplane airplane = null;
        if (airplaneId != null) {
            airplane = airplaneService.getAirplaneById(airplaneId);
        }
        AirplaneModel airplaneModel = null;
        if (airplaneModelId != null) {
            airplaneModel = airplaneModelService.getAirplaneModelById(airplaneModelId);
        }
        Route route = null;
        if (routeId != null) {
            route = routeService.getRouteById(routeId);
        }
        FlightCategory category = null;
        if (categoryId != null) {
            category = flightCategoryService.getFlightCategoryById(categoryId);
        }
        return flightRepository.findAllIgnoringNullBy(airplane, airplaneModel, route, category, ticketPrice);
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Flight not found: " + id));
    }

    @Override
    public void createFlight(FlightCreationRequest creationRequest) {
        Airplane airplane = airplaneService.getAirplaneById(creationRequest.airplaneId());
        Route route = routeService.getRouteById(creationRequest.routeId());
        FlightCategory category = flightCategoryService.getFlightCategoryById(creationRequest.flightCategoryId());
        Flight flight = new Flight();
        flight.setAirplane(airplane);
        flight.setRoute(route);
        flight.setCategory(category);
        flight.setScheduledDepartureAt(creationRequest.scheduledDepartureAt().atStartOfDay());
        flight.setScheduledArrivalAt(creationRequest.scheduledArrivalAt().atStartOfDay());
        flight.setTicketPrice(creationRequest.ticketPrice());
        flight.setMinTicketsNumber(creationRequest.minTicketsNumber());
        flightRepository.save(flight);
    }

    @Override
    public void updateFlight(FlightUpdateRequest updateRequest) {
        Flight flight = getFlightById(updateRequest.id());
        Airplane airplane = airplaneService.getAirplaneById(updateRequest.airplaneId());
        Route route = routeService.getRouteById(updateRequest.routeId());
        FlightCategory category = flightCategoryService.getFlightCategoryById(updateRequest.flightCategoryId());
        flight.setAirplane(airplane);
        flight.setRoute(route);
        flight.setCategory(category);
        flight.setScheduledDepartureAt(updateRequest.scheduledDepartureAt().atStartOfDay());
        flight.setScheduledArrivalAt(updateRequest.scheduledArrivalAt().atStartOfDay());
        flight.setActualDepartureAt(updateRequest.actualDepartureAt());
        flight.setTicketPrice(updateRequest.ticketPrice());
        flight.setMinTicketsNumber(updateRequest.minTicketsNumber());
        flightRepository.save(flight);
    }

    @Override
    public void deleteFlightById(Long id) {
        Flight flight = getFlightById(id);
        flightRepository.delete(flight);
    }

}
