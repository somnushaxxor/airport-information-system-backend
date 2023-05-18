package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

public final class EmployeeMapper {

    private EmployeeMapper() {
    }

    public static EmployeePreviewDto toPreviewDto(Employee employee) {
        String brigadeName = null;
        if (employee.getBrigade() != null) {
            brigadeName = employee.getBrigade().getName();
        }
        return new EmployeePreviewDto(employee.getId(), employee.getFirstName() + " " + employee.getLastName(),
                employee.getGender().getName(), employee.getDateOfBirth(), employee.getJoinedAt(),
                employee.getNumberOfChildren(), employee.getSalary(), employee.getSpecialization().getName(),
                employee.getDepartment().getName(), brigadeName);
    }

    public static EmployeeDto toDto(Employee employee) {
        Long brigadeId = null;
        if (employee.getBrigade() != null) {
            brigadeId = employee.getBrigade().getId();
        }
        return new EmployeeDto(employee.getFirstName(), employee.getLastName(),
                employee.getGender().getId(), employee.getDateOfBirth(), employee.getJoinedAt(),
                employee.getNumberOfChildren(), employee.getSalary(), employee.getSpecialization().getId(),
                employee.getDepartment().getId(), brigadeId);
    }

}
