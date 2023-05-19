package ru.nsu.fit.kolesnik.airportinformationsystem.flight.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightCategoryRepository extends JpaRepository<FlightCategory, Long> {

    boolean existsByName(String name);

}
