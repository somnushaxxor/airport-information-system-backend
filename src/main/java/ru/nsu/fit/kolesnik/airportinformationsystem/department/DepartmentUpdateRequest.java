package ru.nsu.fit.kolesnik.airportinformationsystem.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DepartmentUpdateRequest(@NotNull Long id, @NotBlank String name) {

}
