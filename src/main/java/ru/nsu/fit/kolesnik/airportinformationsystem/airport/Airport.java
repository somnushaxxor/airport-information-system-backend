package ru.nsu.fit.kolesnik.airportinformationsystem.airport;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.Hibernate;
import ru.nsu.fit.kolesnik.airportinformationsystem.city.City;

import java.util.Objects;

@Getter
@Entity
@Table(name = "airports")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Airport airport = (Airport) o;
        return getId() != null && Objects.equals(getId(), airport.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
