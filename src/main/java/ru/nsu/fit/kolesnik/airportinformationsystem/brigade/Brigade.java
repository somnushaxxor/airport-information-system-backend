package ru.nsu.fit.kolesnik.airportinformationsystem.brigade;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.kolesnik.airportinformationsystem.department.Department;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.Specialization;

@Getter
@Setter
@Entity
@Table(name = "brigades")
public class Brigade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "specialization_id", nullable = false)
    private Specialization specialization;

}
