package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    void createEmployee(EmployeeCreationRequest creationRequest);

    void updateEmployee(EmployeeUpdateRequest updateRequest);

    void deleteEmployeeById(Long id);

}
