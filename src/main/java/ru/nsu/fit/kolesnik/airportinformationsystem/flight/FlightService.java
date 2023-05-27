package ru.nsu.fit.kolesnik.airportinformationsystem.flight;

import java.util.List;

public interface FlightService {

    List<Flight> getAllFlightsBy(Long airplaneId, Long airplaneModelId, Long routeId, Long categoryId,
                                 Integer ticketPrice);

    Flight getFlightById(Long id);

    void createFlight(FlightCreationRequest creationRequest);

    void updateFlight(FlightUpdateRequest updateRequest);

    void deleteFlightById(Long id);

}
