package ru.nsu.fit.kolesnik.airportinformationsystem.specialization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

    boolean existsByName(String name);

    Optional<Specialization> findByName(String name);

}
