package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;
import ru.nsu.fit.kolesnik.airportinformationsystem.brigade.Brigade;
import ru.nsu.fit.kolesnik.airportinformationsystem.brigade.BrigadeService;
import ru.nsu.fit.kolesnik.airportinformationsystem.department.Department;
import ru.nsu.fit.kolesnik.airportinformationsystem.department.DepartmentService;
import ru.nsu.fit.kolesnik.airportinformationsystem.gender.Gender;
import ru.nsu.fit.kolesnik.airportinformationsystem.gender.GenderService;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.Specialization;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.SpecializationService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final GenderService genderService;
    private final SpecializationService specializationService;
    private final DepartmentService departmentService;
    private final BrigadeService brigadeService;

    @Override
    public List<Employee> getAllEmployeesBy(Long genderId, Long departmentId, Long brigadeId,
                                            Integer workExperienceInYears, Integer ageInYears,
                                            Integer numberOfChildren, Integer salary) {
        return employeeRepository.findAllIgnoringNullBy(genderId, departmentId, brigadeId, workExperienceInYears, ageInYears,
                numberOfChildren, salary);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found: " + id));
    }

    @Override
    public void createEmployee(EmployeeCreationRequest creationRequest) {
        Gender gender = genderService.getGenderById(creationRequest.genderId());
        LocalDate joinedAt = LocalDate.now();
        if (isJoinedBeforeBorn(creationRequest.dateOfBirth(), joinedAt)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Employee must be born before joining the airport");
        }
        Specialization specialization = specializationService.getSpecializationById(creationRequest.specializationId());
        Department department = departmentService.getDepartmentById(creationRequest.departmentId());
        Brigade brigade = null;
        if (creationRequest.brigadeId() != null) {
            brigade = brigadeService.getBrigadeById(creationRequest.brigadeId());
            if (!department.getBrigades().contains(brigade)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Brigade does not belong to the given department");
            }
            if (!brigade.getSpecialization().equals(specialization)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Brigade is not associated with the given specialization");
            }
        }
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

    private boolean isJoinedBeforeBorn(LocalDate dateOfBirth, LocalDate joinedAt) {
        return dateOfBirth.isAfter(joinedAt);
    }

    @Override
    @Transactional
    public void updateEmployee(EmployeeUpdateRequest updateRequest) {
        Employee employee = getEmployeeById(updateRequest.id());
        Gender gender = genderService.getGenderById(updateRequest.genderId());
        if (isJoinedBeforeBorn(updateRequest.dateOfBirth(), updateRequest.joinedAt())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Employee can not be born after joining the airport");
        }
        Specialization specialization = specializationService.getSpecializationById(updateRequest.specializationId());
        Department department = departmentService.getDepartmentById(updateRequest.departmentId());
        if (isDepartmentChief(employee) && !employee.getDepartment().equals(department)) {
            departmentService.removeDepartmentChief(employee.getDepartment());
        }
        Brigade brigade = null;
        if (updateRequest.brigadeId() != null) {
            brigade = brigadeService.getBrigadeById(updateRequest.brigadeId());
            if (!department.getBrigades().contains(brigade)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Brigade does not belong to the given department");
            }
            if (!brigade.getSpecialization().equals(specialization)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Brigade is not associated with the given specialization");
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

    private boolean isDepartmentChief(Employee employee) {
        return employee.equals(employee.getDepartment().getChief());
    }

    @Override
    @Transactional
    public void deleteEmployeeById(Long id) {
        Employee employee = getEmployeeById(id);
        if (isBrigadeOnlyEmployee(employee) && brigadeService.isBrigadeAssignedToSomeAirplane(employee.getBrigade())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unable to delete the only employee of the brigade which services some airplane");
        }
        if (isDepartmentChief(employee)) {
            departmentService.removeDepartmentChief(employee.getDepartment());
        }
        employeeRepository.delete(employee);
    }

    private boolean isBrigadeOnlyEmployee(Employee employee) {
        return employee.getBrigade().getEmployees().size() == 1;
    }

}
