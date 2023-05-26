package ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute;

import java.util.List;

public interface AttributeService {

    List<Attribute> getAllAttributes();

    Attribute getAttributeById(Long id);

    void createAttribute(AttributeCreationRequest creationRequest);

    void updateAttribute(AttributeUpdateRequest updateRequest);

    void deleteAttributeById(Long id);

}
