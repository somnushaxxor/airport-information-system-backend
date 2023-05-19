package ru.nsu.fit.kolesnik.airportinformationsystem.route;

import jakarta.validation.constraints.NotNull;

public record RouteUpdateRequest(@NotNull Long id, @NotNull Long departureAirportId, @NotNull Long transferAirportId,
                                 @NotNull Long arrivalAirportId) {

}
