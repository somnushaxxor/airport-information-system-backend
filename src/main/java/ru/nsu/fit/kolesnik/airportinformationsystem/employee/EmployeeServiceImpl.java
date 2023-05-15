package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.airportinformationsystem.DeletingDepartmentChiefException;
import ru.nsu.fit.kolesnik.airportinformationsystem.EmployeeCreationException;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;
import ru.nsu.fit.kolesnik.airportinformationsystem.department.Department;
import ru.nsu.fit.kolesnik.airportinformationsystem.department.DepartmentRepository;
import ru.nsu.fit.kolesnik.airportinformationsystem.brigade.Brigade;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.Specialization;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.SpecializationRepository;
import ru.nsu.fit.kolesnik.airportinformationsystem.gender.Gender;
import ru.nsu.fit.kolesnik.airportinformationsystem.gender.GenderRepository;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final GenderRepository genderRepository;
    private final SpecializationRepository specializationRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found: " + id));
    }

    @Override
    public void createEmployee(EmployeeCreationRequest creationRequest) { // TODO hueta
        Gender gender = genderRepository.findById(creationRequest.genderId())
                .orElseThrow(() ->
                        new EmployeeCreationException("Gender not found: " + creationRequest.genderId())
                );
        if (creationRequest.dateOfBirth().isAfter(LocalDate.now())) {
            throw new EmployeeCreationException("Illegal date of birth"); // TODO message
        }
        Specialization specialization = specializationRepository.findById(creationRequest.specializationId())
                .orElseThrow(() ->
                        new EmployeeCreationException("Specialisation not found: " + creationRequest.specializationId())
                );
        Department department = departmentRepository.findById(creationRequest.departmentId())
                .orElseThrow(() ->
                        new EmployeeCreationException("Department not found: " + creationRequest.departmentId())
                );
        Brigade brigade = null;
        if (creationRequest.brigadeId() != null) {
            boolean brigadeFound = false;
            for (Brigade brigadeOfDepartment : department.getBrigades()) {
                if (brigadeOfDepartment.getId().equals(creationRequest.brigadeId())) {
                    brigade = brigadeOfDepartment;
                    brigadeFound = true;
                    break;
                }
            }
            if (!brigadeFound) {
                throw new EmployeeCreationException("Brigade does not belong to the given department: "
                        + creationRequest.brigadeId());
            }
        }
        Employee employee = new Employee();
        employee.setFirstName(creationRequest.firstName());
        employee.setLastName(creationRequest.lastName());
        employee.setGender(gender);
        employee.setDateOfBirth(creationRequest.dateOfBirth());
        employee.setJoinedAt(LocalDate.now());
        employee.setNumberOfChildren(creationRequest.numberOfChildren());
        employee.setSalary(creationRequest.salary());
        employee.setSpecialization(specialization);
        employee.setDepartment(department);
        employee.setBrigade(brigade);
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(EmployeeUpdateRequest updateRequest) {
        // TODO
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Employee employee = getEmployeeById(id);
        if (isDepartmentChief(employee)) {
            throw new DeletingDepartmentChiefException();
        }
        employeeRepository.delete(employee);
    }

    private boolean isDepartmentChief(Employee employee) {
        return employee.equals(employee.getDepartment().getChief());
    }

}
