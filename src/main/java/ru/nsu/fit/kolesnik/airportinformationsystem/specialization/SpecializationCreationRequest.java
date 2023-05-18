package ru.nsu.fit.kolesnik.airportinformationsystem.specialization;

import jakarta.validation.constraints.NotBlank;

public record SpecializationCreationRequest(@NotBlank String name) {

}
