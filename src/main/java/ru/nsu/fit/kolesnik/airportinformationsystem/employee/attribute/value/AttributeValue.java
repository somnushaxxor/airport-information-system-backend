package ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute.value;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.Employee;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute.Attribute;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "attribute_values",
        uniqueConstraints = {@UniqueConstraint(columnNames =
                {"attribute_id", "employee_id"}
        )})
public class AttributeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attribute_id", nullable = false)
    private Attribute attribute;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "value", nullable = false)
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AttributeValue that = (AttributeValue) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
