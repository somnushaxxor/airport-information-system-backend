package ru.nsu.fit.kolesnik.airportinformationsystem.flight;

import java.util.List;

public interface FlightService {

    List<Flight> getAllFlightsBy();

    Flight getFlightById(Long id);

    void createFlight(FlightCreationRequest creationRequest);

    void updateFlight(FlightUpdateRequest updateRequest);

    void deleteFlightById(Long id);

}
