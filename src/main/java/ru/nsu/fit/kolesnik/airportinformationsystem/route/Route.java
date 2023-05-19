package ru.nsu.fit.kolesnik.airportinformationsystem.route;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import ru.nsu.fit.kolesnik.airportinformationsystem.airport.Airport;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "routes",
        uniqueConstraints = {@UniqueConstraint(columnNames =
                {"departure_airport_id", "transfer_airport_id", "arrival_airport_id"}
        )}
)
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id", nullable = false)
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "transfer_airport_id")
    private Airport transferAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id", nullable = false)
    private Airport arrivalAirport;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Route route = (Route) o;
        return getId() != null && Objects.equals(getId(), route.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
