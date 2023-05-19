package ru.nsu.fit.kolesnik.airportinformationsystem.airplane;

import java.time.LocalDate;

public record AirplanePreviewDto(Long id, String modelName, LocalDate createdAt, LocalDate joinedAt,
                                 String pilotsBrigadeName, String techniciansBrigadeName, String serviceBrigadeName,
                                 String homeAirportName, String homeAirportCityName) {

}
