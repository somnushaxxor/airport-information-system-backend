package ru.nsu.fit.kolesnik.airportinformationsystem.airplane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

    @Query(value = """
            select *
            from airplanes
            where (:homeAirportId is null or home_airport_id = :homeAirportId)
            and (cast(:joinedAt as date) is null or joined_at = :joinedAt)
            """, nativeQuery = true)
    List<Airplane> findAllIgnoringNullBy(
            @Param("homeAirportId") Long homeAirportId,
            @Param("joinedAt") LocalDate joinedAt
    );

}
