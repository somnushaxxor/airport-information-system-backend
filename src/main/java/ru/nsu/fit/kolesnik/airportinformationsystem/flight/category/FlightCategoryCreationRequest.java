package ru.nsu.fit.kolesnik.airportinformationsystem.flight.category;

import jakarta.validation.constraints.NotBlank;

public record FlightCategoryCreationRequest(@NotBlank String name) {

}
