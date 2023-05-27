package ru.nsu.fit.kolesnik.airportinformationsystem.ticket;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.kolesnik.airportinformationsystem.flight.Flight;
import ru.nsu.fit.kolesnik.airportinformationsystem.gender.Gender;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "local_passport_number", nullable = false)
    private String localPassportNumber;

    @Column(name = "international_passport_number")
    private String internationalPassportNumber;

    @Column(name = "seat", nullable = false)
    private Integer seat;

    @Column(name = "baggage", nullable = false)
    private Boolean baggage;

}
