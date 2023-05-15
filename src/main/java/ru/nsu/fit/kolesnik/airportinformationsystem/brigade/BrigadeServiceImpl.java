package ru.nsu.fit.kolesnik.airportinformationsystem.brigade;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.airportinformationsystem.department.Department;
import ru.nsu.fit.kolesnik.airportinformationsystem.department.DepartmentService;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.Specialization;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.SpecializationService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BrigadeServiceImpl implements BrigadeService {

    private final SpecializationService specializationService;
    private final DepartmentService departmentService;
    private final BrigadeRepository brigadeRepository;


    @Override
    public List<Brigade> getBrigadesBy(Long specialisationId, Long departmentId) {
        Specialization specialization = null;
        if (specialisationId != null) {
            specialization = specializationService.getSpecializationById(specialisationId);
        }
        Department department = null;
        if (departmentId != null) {
            department = departmentService.getDepartmentById(departmentId);
        }
        Brigade brigadeExample = new Brigade();
        brigadeExample.setSpecialization(specialization);
        brigadeExample.setDepartment(department);
        return brigadeRepository.findAll(Example.of(brigadeExample));
    }

}
