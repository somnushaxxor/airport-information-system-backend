package ru.nsu.fit.kolesnik.airportinformationsystem.pilotmedicalexamination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.Employee;

import java.time.LocalDate;

@Repository
public interface PilotMedicalExaminationRepository extends JpaRepository<PilotMedicalExamination, Long> {

    boolean existsByPilotAndDate(Employee pilot, LocalDate date);

    void deleteAllByPilot(Employee pilot);

}
