package ru.nsu.fit.kolesnik.airportinformationsystem.airplane;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AirplaneCreationRequest(@NotNull Long modelId, @NotNull LocalDate createdAt,
                                      @NotNull Long pilotsBrigadeId, @NotNull Long techniciansBrigadeId,
                                      @NotNull Long serviceBrigadeId, @NotNull Long homeAirportId) {

}
