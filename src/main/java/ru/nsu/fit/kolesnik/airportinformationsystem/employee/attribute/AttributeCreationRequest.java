package ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AttributeCreationRequest(@NotBlank String name, @NotNull Long specializationId) {

}
