package ru.nsu.fit.kolesnik.airportinformationsystem.airplanemaintenanceoperation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.kolesnik.airportinformationsystem.airplane.Airplane;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "airplane_maintenance_operations")
public class AirplaneMaintenanceOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "done_at", nullable = false)
    private LocalDate doneAt;

    @Column(name = "repair_required", nullable = false)
    private Boolean repairRequired;

    @ManyToOne
    @JoinColumn(name = "airplane_id", nullable = false)
    private Airplane airplane;

}
