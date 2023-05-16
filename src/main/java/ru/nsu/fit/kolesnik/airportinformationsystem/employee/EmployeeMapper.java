package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

public final class EmployeeMapper {

    private EmployeeMapper() {
    }

    public static EmployeeDto toDto(Employee employee) {
        Long brigadeId = null;
        if (employee.getBrigade() != null) {
            brigadeId = employee.getBrigade().getId();
        }
        return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(),
                employee.getGender().getId(), employee.getDateOfBirth(), employee.getJoinedAt(),
                employee.getNumberOfChildren(), employee.getSalary(), employee.getSpecialization().getId(),
                employee.getDepartment().getId(), brigadeId);
    }

}
