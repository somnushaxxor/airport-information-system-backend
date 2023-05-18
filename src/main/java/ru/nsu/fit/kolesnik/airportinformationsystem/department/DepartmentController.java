package ru.nsu.fit.kolesnik.airportinformationsystem.department;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

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

}
