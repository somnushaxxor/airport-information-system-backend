package ru.nsu.fit.kolesnik.airportinformationsystem.airplanemaintenanceoperation;

import java.time.LocalDate;

public record AirplaneMaintenanceOperationPreviewDto(Long id, LocalDate doneAt, Boolean repairRequired,
                                                     Long airplaneNumber) {

}
