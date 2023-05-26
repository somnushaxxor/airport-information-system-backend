package ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute.value;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees/attributes/values")
public class AttributeValueController {

    private final AttributeValueService attributeValueService;

    @GetMapping
    public List<AttributeValuePreviewDto> getAllAttributeValuesBy(
            @RequestParam(name = "employeeId", required = false) Long employeeId
    ) {
        return attributeValueService.getAllAttributeValuesBy(employeeId).stream().map(AttributeValueMapper::toPreviewDto).toList();
    }

    @GetMapping("/{id}")
    public AttributeValueDto getAttributeValueById(@PathVariable Long id) {
        return AttributeValueMapper.toDto(attributeValueService.getAttributeValueById(id));
    }

    @PostMapping
    public void createAttributeValue(@Valid @RequestBody AttributeValueCreationRequest creationRequest) {
        attributeValueService.createAttributeValue(creationRequest);
    }

    @PutMapping
    public void updateAttributeValue(@Valid @RequestBody AttributeValueUpdateRequest updateRequest) {
        attributeValueService.updateAttributeValue(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteAttributeValueById(@PathVariable Long id) {
        attributeValueService.deleteAttributeValueById(id);
    }

}
