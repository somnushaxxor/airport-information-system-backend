package ru.nsu.fit.kolesnik.airportinformationsystem.gender;

import jakarta.persistence.*;
import lombok.Getter;

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

}