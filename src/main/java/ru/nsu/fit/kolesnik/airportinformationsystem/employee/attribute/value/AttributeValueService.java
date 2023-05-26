package ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute.value;

import java.util.List;

public interface AttributeValueService {

    List<AttributeValue> getAllAttributeValuesBy(Long employeeId);

    AttributeValue getAttributeValueById(Long id);

    void createAttributeValue(AttributeValueCreationRequest creationRequest);

    void updateAttributeValue(AttributeValueUpdateRequest updateRequest);

    void deleteAttributeValueById(Long id);

}
