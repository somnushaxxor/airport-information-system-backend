package ru.nsu.fit.kolesnik.airportinformationsystem.airplane.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AirplaneModelCreationRequest(@NotBlank String name, @NotNull Integer passengersCapacity) {

}
