package ru.nsu.fit.kolesnik.airportinformationsystem.brigade;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import ru.nsu.fit.kolesnik.airportinformationsystem.department.Department;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.Specialization;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "brigades")
public class Brigade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "specialization_id", nullable = false)
    private Specialization specialization;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Brigade brigade = (Brigade) o;
        return getId() != null && Objects.equals(getId(), brigade.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
