package ru.nsu.fit.kolesnik.airportinformationsystem.flight;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import ru.nsu.fit.kolesnik.airportinformationsystem.airplane.Airplane;
import ru.nsu.fit.kolesnik.airportinformationsystem.flight.category.FlightCategory;
import ru.nsu.fit.kolesnik.airportinformationsystem.route.Route;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "airplane_id", nullable = false)
    private Airplane airplane;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private FlightCategory category;

    @Column(name = "scheduled_departure_at", nullable = false)
    private LocalDateTime scheduledDepartureAt;

    @Column(name = "scheduled_arrival_at", nullable = false)
    private LocalDateTime scheduledArrivalAt;

    @Column(name = "actual_departure_at")
    private LocalDateTime actualDepartureAt;

    @Column(name = "ticket_price", nullable = false)
    private Integer ticketPrice;

    @Column(name = "min_tickets_number", nullable = false)
    private Integer minTicketsNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Flight flight = (Flight) o;
        return getId() != null && Objects.equals(getId(), flight.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
