package ru.nsu.fit.kolesnik.airportinformationsystem.department;

import ru.nsu.fit.kolesnik.airportinformationsystem.employee.Employee;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department getDepartmentById(Long id);

    void createDepartment(DepartmentCreationRequest creationRequest);

    void updateDepartment(DepartmentUpdateRequest updateRequest);

    void deleteDepartmentById(Long id);

    void appointDepartmentChief(Department department, Employee employee);

    void removeDepartmentChief(Department department);

}
