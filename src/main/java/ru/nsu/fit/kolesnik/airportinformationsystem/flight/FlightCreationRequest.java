package ru.nsu.fit.kolesnik.airportinformationsystem.flight;

import jakarta.validation.constraints.NotBlank;

public record FlightCreationRequest(@NotBlank String name) {

}
