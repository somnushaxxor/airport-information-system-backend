package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

public final class EmployeeMapper {

    private EmployeeMapper() {
    }

    public static EmployeeDto toDto(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(),
                employee.getGender().getName(), employee.getDateOfBirth(), employee.getJoinedAt(),
                employee.getNumberOfChildren(), employee.getSalary(), employee.getSpecialization().getName(),
                employee.getDepartment().getName(), employee.getBrigade().getName());
    }

}
