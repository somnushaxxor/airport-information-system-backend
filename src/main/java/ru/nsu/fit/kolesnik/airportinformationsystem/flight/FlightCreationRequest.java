package ru.nsu.fit.kolesnik.airportinformationsystem.flight;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FlightCreationRequest(@NotNull Long airplaneId, @NotNull Long routeId, @NotNull Long flightCategoryId,
                                    @NotNull LocalDate scheduledDepartureAt,
                                    @NotNull LocalDate scheduledArrivalAt,
                                    @Min(0) @NotNull Integer ticketPrice, @Min(0) @NotNull Integer minTicketsNumber) {

}
