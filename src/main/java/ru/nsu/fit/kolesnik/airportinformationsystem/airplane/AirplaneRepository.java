package ru.nsu.fit.kolesnik.airportinformationsystem.airplane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.kolesnik.airportinformationsystem.brigade.Brigade;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

    @Query(value = """
            select *
            from airplanes
            where (:homeAirportId is null or home_airport_id = :homeAirportId)
            and (cast(:joinedAt as date) is null or joined_at = :joinedAt)
            and (:flightsNumber is null or :flightsNumber = :flightsNumber)
            and (:ageInYears is null or date_part('year', age(current_date, created_at)) = :ageInYears)
            """, nativeQuery = true)
    List<Airplane> findAllIgnoringNullBy(
            @Param("homeAirportId") Long homeAirportId,
            @Param("joinedAt") LocalDate joinedAt,
            @Param("flightsNumber") Integer flightsNumber,
            @Param("ageInYears") Integer ageInYears
            );

    boolean existsAirplaneByPilotsBrigade(Brigade pilotsBrigade);

    boolean existsAirplaneByTechniciansBrigade(Brigade technicianBrigade);

    boolean existsAirplaneByServiceBrigade(Brigade serviceBrigade);

}
