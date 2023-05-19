package ru.nsu.fit.kolesnik.airportinformationsystem.flight;

import java.time.LocalDateTime;

public record FlightDto(Long airplaneId, Long routeId, Long categoryId, LocalDateTime scheduledDepartureAt,
                        LocalDateTime scheduledArrivalAt, LocalDateTime actualDepartureAt, Integer ticketPrice,
                        Integer minTicketsNumber) {

}
