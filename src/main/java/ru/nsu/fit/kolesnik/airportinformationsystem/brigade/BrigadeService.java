package ru.nsu.fit.kolesnik.airportinformationsystem.brigade;

import java.util.List;

public interface BrigadeService {

    List<Brigade> getAllBrigadesBy(Long specializationId, Long departmentId);

    Brigade getBrigadeById(Long id);

    void createBrigade(BrigadeCreationRequest creationRequest);

    void updateBrigade(BrigadeUpdateRequest updateRequest);

    void deleteBrigadeById(Long id);

}
