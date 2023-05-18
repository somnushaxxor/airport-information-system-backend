package ru.nsu.fit.kolesnik.airportinformationsystem.airplane.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneModelRepository extends JpaRepository<AirplaneModel, Long> {

    boolean existsByName(String name);

}
