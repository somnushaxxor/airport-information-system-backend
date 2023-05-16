package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

import java.time.LocalDate;

public record EmployeeDto(Long id, String firstName, String lastName, Long genderId, LocalDate dateOfBirth,
                          LocalDate joinedAt, Integer numberOfChildren, Integer salary, Long specializationId,
                          Long departmentId, Long brigadeId) {

}
