package ru.nsu.fit.kolesnik.airportinformationsystem.brigade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BrigadeCreationRequest(@NotBlank String name, @NotNull Long departmentId,
                                     @NotNull Long specializationId) {

}
