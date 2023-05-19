package ru.nsu.fit.kolesnik.airportinformationsystem.pilotmedicalexamination;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.Employee;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.EmployeeService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PilotMedicalExaminationServiceImpl implements PilotMedicalExaminationService {

    private final PilotMedicalExaminationRepository pilotMedicalExaminationRepository;
    private final EmployeeService employeeService;
    @Value("${specializations.core.pilot}")
    private String pilotSpecializationName;

    @Override
    public List<PilotMedicalExamination> getAllPilotsMedicalExaminationsBy() {
        return pilotMedicalExaminationRepository.findAll();
    }

    @Override
    public PilotMedicalExamination getPilotMedicalExaminationById(Long id) {
        return pilotMedicalExaminationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pilot medical examination not found: " + id));
    }

    @Override
    public void createPilotMedicalExamination(PilotMedicalExaminationCreationRequest creationRequest) {
        Employee pilot = employeeService.getEmployeeById(creationRequest.pilotId());
        if (!isPilot(pilot)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Given employee is not a pilot");
        }
        if (pilotMedicalExaminationRepository.existsByPilotAndDate(pilot, creationRequest.date())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Pilot medical examination for this date is already registered");
        }
        PilotMedicalExamination pilotMedicalExamination = new PilotMedicalExamination();
        pilotMedicalExamination.setPilot(pilot);
        pilotMedicalExamination.setDate(creationRequest.date());
        pilotMedicalExaminationRepository.save(pilotMedicalExamination);
    }

    private boolean isPilot(Employee employee) {
        return pilotSpecializationName.equals(employee.getSpecialization().getName());
    }

    @Override
    public void updatePilotMedicalExamination(PilotMedicalExaminationUpdateRequest updateRequest) {
        PilotMedicalExamination pilotMedicalExamination = getPilotMedicalExaminationById(updateRequest.id());
        Employee pilot = employeeService.getEmployeeById(updateRequest.pilotId());
        if (!isPilot(pilot)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Given employee is not a pilot");
        }
        if (pilotMedicalExaminationRepository.existsByPilotAndDate(pilot, updateRequest.date())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Pilot medical examination for this date is already registered");
        }
        pilotMedicalExamination.setPilot(pilot);
        pilotMedicalExamination.setDate(updateRequest.date());
        pilotMedicalExaminationRepository.save(pilotMedicalExamination);
    }

    @Override
    public void deletePilotMedicalExaminationById(Long id) {
        PilotMedicalExamination pilotMedicalExamination = getPilotMedicalExaminationById(id);
        pilotMedicalExaminationRepository.delete(pilotMedicalExamination);
    }

}
