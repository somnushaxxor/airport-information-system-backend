package ru.nsu.fit.kolesnik.airportinformationsystem.airplane;

import java.time.LocalDate;

public record AirplaneDto(Long modelId, LocalDate createdAt, LocalDate joinedAt,
                          Long pilotsBrigadeId, Long techniciansBrigadeId, Long serviceBrigadeId,
                          Long homeAirportId) {

}
