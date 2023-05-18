package ru.nsu.fit.kolesnik.airportinformationsystem.department;

import jakarta.validation.constraints.NotNull;

public record DepartmentChiefAppointmentRequest(@NotNull Long departmentId, @NotNull Long employeeId) {

}
