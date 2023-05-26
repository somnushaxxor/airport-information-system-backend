package ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.Specialization;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {

    boolean existsByNameAndSpecialization(String name, Specialization specialization);

}
