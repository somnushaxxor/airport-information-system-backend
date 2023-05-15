package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

import java.time.LocalDate;

public record EmployeeDto(Long id, String firstName, String lastName, String gender, LocalDate dateOfBirth,
                          LocalDate joinedAt, Integer numberOfChildren, Integer salary, String specialization,
                          String department, String brigade) {

}
