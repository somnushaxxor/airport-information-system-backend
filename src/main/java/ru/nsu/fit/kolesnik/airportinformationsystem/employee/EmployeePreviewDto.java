package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

import java.time.LocalDate;

public record EmployeePreviewDto(Long id, String name, String genderName, LocalDate dateOfBirth,
                                 LocalDate joinedAt, Integer numberOfChildren, Integer salary,
                                 String specializationName, String departmentName, String brigadeName) {

}
