package ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute.value;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.Employee;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.EmployeeService;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute.Attribute;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute.AttributeService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AttributeValueServiceImpl implements AttributeValueService {

    private final AttributeValueRepository attributeValueRepository;
    private final AttributeService attributeService;
    private final EmployeeService employeeService;

    @Override
    public List<AttributeValue> getAllAttributeValuesBy(Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return attributeValueRepository.findAllByEmployee(employee);
    }

    @Override
    public AttributeValue getAttributeValueById(Long id) {
        return attributeValueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Attribute value not found: " + id));
    }

    @Override
    public void createAttributeValue(AttributeValueCreationRequest creationRequest) {
        Attribute attribute = attributeService.getAttributeById(creationRequest.attributeId());
        Employee employee = employeeService.getEmployeeById(creationRequest.employeeId());
        if (!employee.getSpecialization().equals(attribute.getSpecialization())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The employee's specialization does not correspond to the given attribute");
        }
        if (attributeValueRepository.existsByAttributeAndEmployee(attribute, employee)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The attribute already has some value for the given employee");
        }
        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setAttribute(attribute);
        attributeValue.setEmployee(employee);
        attributeValue.setValue(creationRequest.value());
        attributeValueRepository.save(attributeValue);
    }

    @Override
    public void updateAttributeValue(AttributeValueUpdateRequest updateRequest) {
        AttributeValue attributeValue = getAttributeValueById(updateRequest.id());
        attributeValue.setValue(updateRequest.value());
        attributeValueRepository.save(attributeValue);
    }

    @Override
    public void deleteAttributeValueById(Long id) {
        AttributeValue attributeValue = getAttributeValueById(id);
        attributeValueRepository.delete(attributeValue);
    }

}
