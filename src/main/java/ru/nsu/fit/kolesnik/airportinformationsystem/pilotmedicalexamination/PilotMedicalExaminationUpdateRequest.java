package ru.nsu.fit.kolesnik.airportinformationsystem.pilotmedicalexamination;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PilotMedicalExaminationUpdateRequest(@NotNull Long id, @NotNull Long pilotId, @NotNull LocalDate date) {

}
