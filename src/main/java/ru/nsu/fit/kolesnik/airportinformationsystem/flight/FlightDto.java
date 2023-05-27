package ru.nsu.fit.kolesnik.airportinformationsystem.flight;

import java.time.LocalDate;

public record FlightDto(Long airplaneId, Long routeId, Long categoryId, LocalDate scheduledDepartureAt,
                        LocalDate scheduledArrivalAt, LocalDate actualDepartureAt, Integer ticketPrice,
                        Integer minTicketsNumber) {

}
