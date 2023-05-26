package ru.nsu.fit.kolesnik.airportinformationsystem.airplanemaintenanceoperation;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AirplaneMaintenanceOperationUpdateRequest(@NotNull Long id, @NotNull LocalDate doneAt,
                                                        @NotNull Boolean repairRequired, @NotNull Long airplaneId) {

}
