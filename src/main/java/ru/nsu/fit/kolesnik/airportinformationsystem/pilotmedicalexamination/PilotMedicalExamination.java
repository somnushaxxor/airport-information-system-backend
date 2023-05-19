package ru.nsu.fit.kolesnik.airportinformationsystem.pilotmedicalexamination;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.Employee;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "pilots_medical_examinations",
        uniqueConstraints = {@UniqueConstraint(columnNames =
                {"pilot_id", "date"}
        )}
)
public class PilotMedicalExamination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pilot_id", nullable = false)
    private Employee pilot;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PilotMedicalExamination that = (PilotMedicalExamination) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
