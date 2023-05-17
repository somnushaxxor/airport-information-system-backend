package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;
import ru.nsu.fit.kolesnik.airportinformationsystem.brigade.Brigade;
import ru.nsu.fit.kolesnik.airportinformationsystem.brigade.BrigadeService;
import ru.nsu.fit.kolesnik.airportinformationsystem.department.Department;
import ru.nsu.fit.kolesnik.airportinformationsystem.department.DepartmentRepository;
import ru.nsu.fit.kolesnik.airportinformationsystem.department.DepartmentService;
import ru.nsu.fit.kolesnik.airportinformationsystem.gender.Gender;
import ru.nsu.fit.kolesnik.airportinformationsystem.gender.GenderRepository;
import ru.nsu.fit.kolesnik.airportinformationsystem.gender.GenderService;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.Specialization;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.SpecializationRepository;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.SpecializationService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final GenderRepository genderRepository;
    private final SpecializationRepository specializationRepository;
    private final DepartmentRepository departmentRepository;
    private final GenderService genderService;
    private final SpecializationService specializationService;
    private final DepartmentService departmentService;
    private final BrigadeService brigadeService;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getAllEmployeesFiltered(Long genderId, Long departmentId, Long brigadeId,
                                                  Integer workExperienceInYears, Integer ageInYears,
                                                  Integer numberOfChildren, Integer salary) {
        return employeeRepository.findAllBy(genderId, departmentId, brigadeId, workExperienceInYears, ageInYears,
                numberOfChildren, salary);
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
        LocalDate joinedAt = LocalDate.now();
        if (creationRequest.dateOfBirth().isAfter(joinedAt)) {
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
        // TODO brigade depends on dep and specialization
        Employee employee = new Employee();
        employee.setFirstName(creationRequest.firstName());
        employee.setLastName(creationRequest.lastName());
        employee.setGender(gender);
        employee.setDateOfBirth(creationRequest.dateOfBirth());
        employee.setJoinedAt(joinedAt);
        employee.setNumberOfChildren(creationRequest.numberOfChildren());
        employee.setSalary(creationRequest.salary());
        employee.setSpecialization(specialization);
        employee.setDepartment(department);
        employee.setBrigade(brigade);
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(EmployeeUpdateRequest updateRequest) {
        Employee employee = getEmployeeById(updateRequest.id());
        Gender gender = genderService.getGenderById(updateRequest.genderId());
        if (updateRequest.dateOfBirth().isAfter(updateRequest.joinedAt())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Illegal date of birth"); // TODO hueta
        }
        Specialization specialization = specializationService.getSpecializationById(updateRequest.specializationId());
        Department department = departmentService.getDepartmentById(updateRequest.departmentId());
        if (isDepartmentChief(employee) && !department.equals(employee.getDepartment())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "HUETA"); // TODO hueta
        }
        Brigade brigade = null;
        if (updateRequest.brigadeId() != null) {
            brigade = brigadeService.getBrigadeById(updateRequest.brigadeId());
            if (!department.getBrigades().contains(brigade) || !brigade.getSpecialization().equals(specialization)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "HUETA"); // TODO hueta
            }
        }
        employee.setFirstName(updateRequest.firstName());
        employee.setLastName(updateRequest.lastName());
        employee.setGender(gender);
        employee.setDateOfBirth(updateRequest.dateOfBirth());
        employee.setJoinedAt(updateRequest.joinedAt());
        employee.setNumberOfChildren(updateRequest.numberOfChildren());
        employee.setSalary(updateRequest.salary());
        employee.setSpecialization(specialization);
        employee.setDepartment(department);
        employee.setBrigade(brigade);
        employeeRepository.save(employee);
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
