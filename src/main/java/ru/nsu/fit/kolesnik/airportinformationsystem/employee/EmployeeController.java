package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees().stream().map(EmployeeMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return EmployeeMapper.toDto(employeeService.getEmployeeById(id));
    }

    @PostMapping
    public void createEmployee(@Valid @RequestBody EmployeeCreationRequest creationRequest) {
        employeeService.createEmployee(creationRequest);
    }

    @PutMapping
    public void updateEmployee(@Valid @RequestBody EmployeeUpdateRequest updateRequest) {
        employeeService.updateEmployee(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
    }

}
