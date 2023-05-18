package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeePreviewDto> getAllEmployeesBy(
            @RequestParam(value = "genderId", required = false) Long genderId,
            @RequestParam(value = "departmentId", required = false) Long departmentId,
            @RequestParam(value = "brigadeId", required = false) Long brigadeId,
            @Min(0) @RequestParam(value = "workExperienceInYears", required = false) Integer workExperienceInYears,
            @Min(0) @RequestParam(value = "ageInYears", required = false) Integer ageInYears,
            @Min(0) @RequestParam(value = "numberOfChildren", required = false) Integer numberOfChildren,
            @Min(0) @RequestParam(value = "salary", required = false) Integer salary
    ) {
        return employeeService.getAllEmployeesBy(genderId, departmentId, brigadeId, workExperienceInYears,
                ageInYears, numberOfChildren, salary).stream().map(EmployeeMapper::toPreviewDto).toList();
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
