package ru.nsu.fit.kolesnik.airportinformationsystem.flight;

import java.time.LocalDateTime;

public record FlightPreviewDto(Long id, Long airplaneNumber, String departureAirportName, String departureCityName,
                               String transferAirportName, String transferCityName, String arrivalAirportName,
                               String arrivalCityName, String categoryName, LocalDateTime scheduledDepartureAt,
                               LocalDateTime scheduledArrivalAt, LocalDateTime actualDepartureAt, Integer ticketPrice,
                               Integer minTicketsNumber) {

}
