package ru.nsu.fit.kolesnik.airportinformationsystem.airplane.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AirplaneModelCreationRequest(@NotBlank String name, @Min(0) @NotNull Integer passengersCapacity) {

}
