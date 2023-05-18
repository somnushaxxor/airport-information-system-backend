package ru.nsu.fit.kolesnik.airportinformationsystem.airplane.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AirplaneModelUpdateRequest(@NotNull Long id, @NotBlank String name, @NotNull Integer passengersCapacity) {

}
