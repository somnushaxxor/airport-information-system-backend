package ru.nsu.fit.kolesnik.airportinformationsystem.department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department getDepartmentById(Long id);

    void createDepartment(DepartmentCreationRequest creationRequest);

    void updateDepartment(DepartmentUpdateRequest updateRequest);

    void deleteDepartmentById(Long id);

}
