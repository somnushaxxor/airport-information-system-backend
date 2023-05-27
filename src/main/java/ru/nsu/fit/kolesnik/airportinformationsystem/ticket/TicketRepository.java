package ru.nsu.fit.kolesnik.airportinformationsystem.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = """
            select *
            from tickets
            where (:flightId is null or flight_id = :flightId)
            and (:genderId is null or gender_id = :genderId)
            and (:ageInYears is null or date_part('year', age(current_date, date_of_birth)) = :ageInYears)
            """, nativeQuery = true)
    List<Ticket> findAllIgnoringNullBy(
            @Param("flightId") Long flightId,
            @Param("genderId") Long genderId,
            @Param("ageInYears") Integer ageInYears
    );

}
