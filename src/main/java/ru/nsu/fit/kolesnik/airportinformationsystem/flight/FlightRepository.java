package ru.nsu.fit.kolesnik.airportinformationsystem.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.kolesnik.airportinformationsystem.airplane.Airplane;
import ru.nsu.fit.kolesnik.airportinformationsystem.airplane.model.AirplaneModel;
import ru.nsu.fit.kolesnik.airportinformationsystem.flight.category.FlightCategory;
import ru.nsu.fit.kolesnik.airportinformationsystem.route.Route;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("""
            select f
            from Flight f
            where (:airplane is null or f.airplane = :airplane)
            and (:airplaneModel is null or f.airplane.model = :airplaneModel)
            and (:route is null or f.route = :route)
            and (:category is null or f.category = :category)
            and (:ticketPrice is null or f.ticketPrice = :ticketPrice)
            """)
    List<Flight> findAllIgnoringNullBy(
            @Param("airplane") Airplane airplane,
            @Param("airplaneModel") AirplaneModel airplaneModel,
            @Param("route") Route route,
            @Param("category") FlightCategory category,
            @Param("ticketPrice") Integer ticketPrice
    );

}
