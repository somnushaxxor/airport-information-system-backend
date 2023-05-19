package ru.nsu.fit.kolesnik.airportinformationsystem.route;

public record RoutePreviewDto(Long id, String departureAirportName, String departureCityName,
                              String transferAirportName, String transferCityName,
                              String arrivalAirportName, String arrivalCityName) {

}
