package ru.nsu.fit.kolesnik.airportinformationsystem.airplanemaintenanceoperation;

import java.time.LocalDate;

public record AirplaneMaintenanceOperationDto(LocalDate doneAt, Boolean repairRequired, Long airplaneId) {

}
