package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployeesBy(Long genderId, Long departmentId, Long brigadeId,
                                     Integer workExperienceInYears, Integer ageInYears,
                                     Integer numberOfChildren, Integer salary);

    Employee getEmployeeById(Long id);

    void createEmployee(EmployeeCreationRequest creationRequest);

    void updateEmployee(EmployeeUpdateRequest updateRequest);

    void deleteEmployeeById(Long id);

    List<Employee> getAllPilots();

    List<Employee> getAllPilotsBy(Integer medicalExaminationYear, Long genderId, Integer ageInYears, Integer salary);

}
