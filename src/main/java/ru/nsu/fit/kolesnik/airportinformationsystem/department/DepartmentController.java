package ru.nsu.fit.kolesnik.airportinformationsystem.department;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.Employee;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.EmployeeService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    @GetMapping
    public List<DepartmentPreviewDto> getAllDepartments() {
        return departmentService.getAllDepartments().stream().map(DepartmentMapper::toPreviewDto).toList();
    }

    @GetMapping("/{id}")
    public DepartmentDto getDepartmentById(@PathVariable Long id) {
        return DepartmentMapper.toDto(departmentService.getDepartmentById(id));
    }

    @PostMapping
    public void createDepartment(@Valid @RequestBody DepartmentCreationRequest creationRequest) {
        departmentService.createDepartment(creationRequest);
    }

    @PutMapping
    public void updateDepartment(@Valid @RequestBody DepartmentUpdateRequest updateRequest) {
        departmentService.updateDepartment(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartmentById(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
    }

    @PostMapping("/chief-appointment")
    public void appointDepartmentChief(@Valid @RequestBody DepartmentChiefAppointmentRequest appointmentRequest) {
        Department department = departmentService.getDepartmentById(appointmentRequest.departmentId());
        Employee employee = employeeService.getEmployeeById(appointmentRequest.employeeId());
        departmentService.appointDepartmentChief(department, employee);
    }

    @PostMapping("/{id}/chief-removal")
    public void removeDepartmentChief(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        departmentService.removeDepartmentChief(department);
    }

}
