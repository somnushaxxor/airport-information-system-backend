package ru.nsu.fit.kolesnik.airportinformationsystem.flight;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FlightUpdateRequest(@NotNull Long id, @NotNull Long airplaneId, @NotNull Long routeId,
                                  @NotNull Long flightCategoryId,
                                  @NotNull LocalDate scheduledDepartureAt,
                                  @NotNull LocalDate scheduledArrivalAt, @Nullable LocalDateTime actualDepartureAt,
                                  @Min(0) @NotNull Integer ticketPrice, @Min(0) @NotNull Integer minTicketsNumber) {

}