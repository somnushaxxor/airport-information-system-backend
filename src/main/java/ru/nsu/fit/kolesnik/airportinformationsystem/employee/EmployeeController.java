package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

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

    @PostMapping
    public void createEmployee(@RequestBody EmployeeCreationRequest creationRequest) {
        employeeService.createEmployee(creationRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
    }

}
