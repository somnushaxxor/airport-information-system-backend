package ru.nsu.fit.kolesnik.airportinformationsystem.flight.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FlightCategoryUpdateRequest(@NotNull Long id, @NotBlank String name) {

}
