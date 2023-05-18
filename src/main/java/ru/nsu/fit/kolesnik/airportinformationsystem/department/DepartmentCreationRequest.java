package ru.nsu.fit.kolesnik.airportinformationsystem.department;

import jakarta.validation.constraints.NotBlank;

public record DepartmentCreationRequest(@NotBlank String name) {

}
