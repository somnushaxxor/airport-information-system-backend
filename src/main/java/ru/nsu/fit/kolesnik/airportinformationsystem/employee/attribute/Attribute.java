package ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.Specialization;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "attributes",
        uniqueConstraints = {@UniqueConstraint(columnNames =
                {"name", "specialization_id"}
        )})
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "specialization_id", nullable = false)
    private Specialization specialization;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Attribute attribute = (Attribute) o;
        return getId() != null && Objects.equals(getId(), attribute.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
