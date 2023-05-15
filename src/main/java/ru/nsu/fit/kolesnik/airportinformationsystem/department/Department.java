package ru.nsu.fit.kolesnik.airportinformationsystem.department;

import jakarta.persistence.*;
import lombok.Getter;
import ru.nsu.fit.kolesnik.airportinformationsystem.brigade.Brigade;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.Employee;

import java.util.Set;

@Getter
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "chief_id", nullable = false)
    private Employee chief;

    @OneToMany(mappedBy = "department")
    private Set<Brigade> brigades;

}
