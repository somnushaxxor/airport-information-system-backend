package ru.nsu.fit.kolesnik.airportinformationsystem.specialization;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.EmployeeRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SpecializationServiceImpl implements SpecializationService {

    private final SpecializationRepository specializationRepository;
    private final EmployeeRepository employeeRepository;
    @Value("${specializations.core.pilot}")
    private String pilotSpecializationName;
    @Value("${specializations.core.technician}")
    private String technicianSpecializationName;
    @Value("${specializations.core.service}")
    private String serviceSpecializationName;

    @Override
    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    @Override
    public Specialization getSpecializationById(Long id) {
        return specializationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Specialization not found: " + id));
    }

    @Override
    public void createSpecialization(SpecializationCreationRequest creationRequest) {
        if (specializationRepository.existsByName(creationRequest.name())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Specialization name must be unique: " + creationRequest.name());
        }
        Specialization specialization = new Specialization();
        specialization.setName(creationRequest.name());
        specializationRepository.save(specialization);
    }

    @Override
    public void updateSpecialization(SpecializationUpdateRequest updateRequest) {
        Specialization specialization = getSpecializationById(updateRequest.id());
        if (specializationRepository.existsByName(updateRequest.name())
                && !specialization.getName().equals(updateRequest.name())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Specialization name must be unique: " + updateRequest.name());
        }
        if (isCoreSpecialization(specialization) && !specialization.getName().equals(updateRequest.name())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unable to rename core specialization: " + specialization.getName());
        }
        specialization.setName(updateRequest.name());
        specializationRepository.save(specialization);
    }

    @Override
    @Transactional
    public void deleteSpecializationById(Long id) {
        Specialization specialization = getSpecializationById(id);
        if (isCoreSpecialization(specialization)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unable to delete core specialization: " + specialization.getName());
        }
        employeeRepository.deleteAllBySpecialization(specialization);
        specializationRepository.delete(specialization);
    }

    private boolean isCoreSpecialization(Specialization specialization) {
        return specialization.getName().equals(pilotSpecializationName)
                || specialization.getName().equals(technicianSpecializationName)
                || specialization.getName().equals(serviceSpecializationName);
    }

    @Override
    public Specialization getSpecializationByName(String name) {
        return specializationRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Specialization not found: " + name));
    }

}
