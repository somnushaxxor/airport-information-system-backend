package ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees/attributes")
public class AttributeController {

    private final AttributeService attributeService;

    @GetMapping
    public List<AttributePreviewDto> getAllAttributes() {
        return attributeService.getAllAttributes().stream().map(AttributeMapper::toPreviewDto).toList();
    }

    @GetMapping("/{id}")
    public AttributeDto getAttributeById(@PathVariable Long id) {
        return AttributeMapper.toDto(attributeService.getAttributeById(id));
    }

    @PostMapping
    public void createAttribute(@Valid @RequestBody AttributeCreationRequest creationRequest) {
        attributeService.createAttribute(creationRequest);
    }

    @PutMapping
    public void updateAttribute(@Valid @RequestBody AttributeUpdateRequest updateRequest) {
        attributeService.updateAttribute(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteAttributeById(@PathVariable Long id) {
        attributeService.deleteAttributeById(id);
    }

}
