package ru.nsu.fit.kolesnik.airportinformationsystem.airplanemaintenanceoperation;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AirplaneMaintenanceOperationCreationRequest(@NotNull LocalDate doneAt, @NotNull Boolean repairRequired,
                                                          @NotNull Long airplaneId) {

}
