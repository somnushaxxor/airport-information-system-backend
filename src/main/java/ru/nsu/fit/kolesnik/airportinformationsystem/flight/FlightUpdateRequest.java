package ru.nsu.fit.kolesnik.airportinformationsystem.flight;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FlightUpdateRequest(@NotNull Long id, @NotBlank String name) {

}
