package ru.nsu.fit.kolesnik.airportinformationsystem.specialization;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SpecializationUpdateRequest(@NotNull Long id, @NotBlank String name) {

}
