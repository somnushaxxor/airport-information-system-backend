package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmployeeUpdateRequest(@NotNull Long id, @NotBlank String firstName, @NotBlank String lastName,
                                    @NotNull Long genderId,
                                    @NotNull LocalDate dateOfBirth, @NotNull LocalDate joinedAt,
                                    @NotNull @Min(0) Integer numberOfChildren,
                                    @NotNull @Min(0) Integer salary, @NotNull Long specializationId,
                                    @NotNull Long departmentId, @Nullable Long brigadeId) {

}
