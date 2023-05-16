package ru.nsu.fit.kolesnik.airportinformationsystem.brigade;

import java.util.List;

public interface BrigadeService {

    Brigade getBrigadeById(Long id);

    List<Brigade> getBrigadesBySpecializationIdAndDepartmentIdIgnoringNull(Long specializationId, Long departmentId);

}
