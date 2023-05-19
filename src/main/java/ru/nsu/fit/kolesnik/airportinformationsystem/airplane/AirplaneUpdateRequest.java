package ru.nsu.fit.kolesnik.airportinformationsystem.airplane;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AirplaneUpdateRequest(@NotNull Long id, @NotNull Long modelId, @NotNull LocalDate createdAt,
                                    @NotNull LocalDate joinedAt, @NotNull Long pilotsBrigadeId,
                                    @NotNull Long techniciansBrigadeId, @NotNull Long serviceBrigadeId,
                                    @NotNull Long homeAirportId) {

}
