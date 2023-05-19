package ru.nsu.fit.kolesnik.airportinformationsystem.route;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public record RouteCreationRequest(@NotNull Long departureAirportId, @Nullable Long transferAirportId,
                                   @NotNull Long arrivalAirportId) {

}
