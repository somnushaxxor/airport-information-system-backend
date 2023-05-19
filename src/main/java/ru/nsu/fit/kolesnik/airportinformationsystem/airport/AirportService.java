package ru.nsu.fit.kolesnik.airportinformationsystem.airport;

import java.util.List;

public interface AirportService {

    List<Airport> getAllAirports();

    Airport getAirportById(Long id);

}
