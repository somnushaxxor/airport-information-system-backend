package ru.nsu.fit.kolesnik.airportinformationsystem.route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.kolesnik.airportinformationsystem.airport.Airport;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    boolean existsByDepartureAirportAndTransferAirportAndArrivalAirport(
            Airport departureAirport, Airport transferAirport, Airport arrivalAirport);

}
