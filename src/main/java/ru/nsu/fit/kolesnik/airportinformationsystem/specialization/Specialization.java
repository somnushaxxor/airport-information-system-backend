package ru.nsu.fit.kolesnik.airportinformationsystem.specialization;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "specializations")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

}
