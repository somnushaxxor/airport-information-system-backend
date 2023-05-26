package ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute.value;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.Employee;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute.Attribute;

import java.util.List;

@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {

    List<AttributeValue> findAllByEmployee(Employee employee);

    boolean existsByAttributeAndEmployee(Attribute attribute, Employee employee);

    boolean existsByAttribute(Attribute attribute);

    void deleteAllByAttribute(Attribute attribute);

    void deleteAllByEmployee(Employee employee);

}
