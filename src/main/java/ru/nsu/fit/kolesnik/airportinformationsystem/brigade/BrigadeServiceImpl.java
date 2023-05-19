package ru.nsu.fit.kolesnik.airportinformationsystem.brigade;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;
import ru.nsu.fit.kolesnik.airportinformationsystem.department.Department;
import ru.nsu.fit.kolesnik.airportinformationsystem.department.DepartmentService;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.EmployeeRepository;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.Specialization;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.SpecializationService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BrigadeServiceImpl implements BrigadeService {

    private final SpecializationService specializationService;
    private final DepartmentService departmentService;
    private final BrigadeRepository brigadeRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<Brigade> getAllBrigadesBy(Long specialisationId,
                                          Long departmentId) {
        Specialization specialization = null;
        if (specialisationId != null) {
            specialization = specializationService.getSpecializationById(specialisationId);
        }
        Department department = null;
        if (departmentId != null) {
            department = departmentService.getDepartmentById(departmentId);
        }
        return brigadeRepository.findAllIgnoringNullBy(department, specialization);
    }

    @Override
    public Brigade getBrigadeById(Long id) {
        return brigadeRepository.findById(id).orElseThrow(() -> new NotFoundException("Brigade not found: " + id));
    }

    @Override
    public void createBrigade(BrigadeCreationRequest creationRequest) {
        Department department = departmentService.getDepartmentById(creationRequest.departmentId());
        Specialization specialization = specializationService.getSpecializationById(creationRequest.specializationId());
        if (brigadeRepository.existsByName(creationRequest.name())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Brigade name must be unique: " + creationRequest.name());
        }
        Brigade brigade = new Brigade();
        brigade.setName(creationRequest.name());
        brigade.setDepartment(department);
        brigade.setSpecialization(specialization);
        brigadeRepository.save(brigade);
    }

    @Override
    public void updateBrigade(BrigadeUpdateRequest updateRequest) {
        Brigade brigade = getBrigadeById(updateRequest.id());
        Department department = departmentService.getDepartmentById(updateRequest.departmentId());
        Specialization specialization = specializationService.getSpecializationById(updateRequest.specializationId());
        if (brigadeRepository.existsByName(updateRequest.name())
                && !brigade.getName().equals(updateRequest.name())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Brigade name must be unique: " + updateRequest.name());
        }
        if (!brigade.getEmployees().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "There should be no employees in the brigade to update it");
        }
        brigade.setName(updateRequest.name());
        brigade.setDepartment(department);
        brigade.setSpecialization(specialization);
        brigadeRepository.save(brigade);
    }

    @Override
    @Transactional
    public void deleteBrigadeById(Long id) {
        Brigade brigade = getBrigadeById(id);
        employeeRepository.deleteAllByBrigade(brigade);
        brigadeRepository.delete(brigade);
    }

}
