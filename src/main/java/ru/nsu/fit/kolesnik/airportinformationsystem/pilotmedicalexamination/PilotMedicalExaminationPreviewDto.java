package ru.nsu.fit.kolesnik.airportinformationsystem.pilotmedicalexamination;

import java.time.LocalDate;

public record PilotMedicalExaminationPreviewDto(Long id, String pilotFirstName, String pilotLastName, LocalDate date) {

}
