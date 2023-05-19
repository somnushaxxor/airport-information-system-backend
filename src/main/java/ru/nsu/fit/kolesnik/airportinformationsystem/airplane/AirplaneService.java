package ru.nsu.fit.kolesnik.airportinformationsystem.airplane;

import java.time.LocalDate;
import java.util.List;

public interface AirplaneService {

    List<Airplane> getAllAirplanesBy(Long homeAirportId, LocalDate joinedAt, Integer flightsNumber);

    Airplane getAirplaneById(Long id);

    void createAirplane(AirplaneCreationRequest creationRequest);

    void updateAirplane(AirplaneUpdateRequest updateRequest);

    void deleteAirplaneById(Long id);

}
