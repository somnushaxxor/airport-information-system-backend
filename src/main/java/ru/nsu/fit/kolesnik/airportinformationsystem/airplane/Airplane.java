package ru.nsu.fit.kolesnik.airportinformationsystem.airplane;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import ru.nsu.fit.kolesnik.airportinformationsystem.airplane.model.AirplaneModel;
import ru.nsu.fit.kolesnik.airportinformationsystem.airport.Airport;
import ru.nsu.fit.kolesnik.airportinformationsystem.brigade.Brigade;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "airplanes")
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private AirplaneModel model;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "joined_at", nullable = false)
    private LocalDate joinedAt;

    @ManyToOne
    @JoinColumn(name = "pilots_brigade_id", nullable = false)
    private Brigade pilotsBrigade;

    @ManyToOne
    @JoinColumn(name = "technicians_brigade_id", nullable = false)
    private Brigade techniciansBrigade;

    @ManyToOne
    @JoinColumn(name = "service_brigade_id", nullable = false)
    private Brigade serviceBrigade;

    @ManyToOne
    @JoinColumn(name = "home_airport_id", nullable = false)
    private Airport homeAirport;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Airplane airplane = (Airplane) o;
        return getId() != null && Objects.equals(getId(), airplane.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
