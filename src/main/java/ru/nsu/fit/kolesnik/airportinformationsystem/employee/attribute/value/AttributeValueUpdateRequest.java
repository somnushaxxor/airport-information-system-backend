package ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute.value;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AttributeValueUpdateRequest(@NotNull Long id, @NotBlank String value) {

}
