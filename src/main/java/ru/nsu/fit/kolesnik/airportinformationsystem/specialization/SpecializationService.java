package ru.nsu.fit.kolesnik.airportinformationsystem.specialization;

import java.util.List;

public interface SpecializationService {

    List<Specialization> getAllSpecializations();

    Specialization getSpecializationById(Long id);

    void createSpecialization(SpecializationCreationRequest creationRequest);

    void updateSpecialization(SpecializationUpdateRequest updateRequest);

    void deleteSpecializationById(Long id);

}
