package ru.nsu.fit.kolesnik.airportinformationsystem.airplanemaintenanceoperation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AirplaneMaintenanceOperationRepository extends JpaRepository<AirplaneMaintenanceOperation, Long> {

    boolean existsByDoneAt(LocalDate doneAt);

}
