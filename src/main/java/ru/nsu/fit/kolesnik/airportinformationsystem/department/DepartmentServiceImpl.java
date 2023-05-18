package ru.nsu.fit.kolesnik.airportinformationsystem.department;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.Employee;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.EmployeeRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;


    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department not found: " + id));
    }

    @Override
    public void createDepartment(DepartmentCreationRequest creationRequest) {
        if (departmentRepository.existsByName(creationRequest.name())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Department name must be unique: " + creationRequest.name());
        }
        Department department = new Department();
        department.setName(creationRequest.name());
        departmentRepository.save(department);

    }

    @Override
    public void updateDepartment(DepartmentUpdateRequest updateRequest) {
        Department department = getDepartmentById(updateRequest.id());
        if (departmentRepository.existsByName(updateRequest.name())
                && !department.getName().equals(updateRequest.name())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Department name must be unique: " + updateRequest.name());
        }
        department.setName(updateRequest.name());
        departmentRepository.save(department);
    }

    @Override
    @Transactional
    public void deleteDepartmentById(Long id) {
        Department department = getDepartmentById(id);
        employeeRepository.deleteAllByDepartment(department);
        departmentRepository.delete(department);
    }

    @Override
    public void appointDepartmentChief(Department department, Employee employee) {
        if (!department.equals(employee.getDepartment())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Employee must belong to the given department");
        }
        department.setChief(employee);
        departmentRepository.save(department);
    }

    @Override
    public void removeDepartmentChief(Department department) {
        department.setChief(null);
        departmentRepository.save(department);
    }

}
