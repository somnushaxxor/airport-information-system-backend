package ru.nsu.fit.kolesnik.airportinformationsystem.gender;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Entity
@Table(name = "genders")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Gender gender = (Gender) o;
        return getId() != null && Objects.equals(getId(), gender.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}